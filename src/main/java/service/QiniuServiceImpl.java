package service;

import com.qiniu.common.QiniuException;
import com.qiniu.common.Zone;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import entity.QiniuEntity;
import mapper.QiniuMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vo.FileVo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yan
 * @date 2018/11/30 16:43
 * @descripition
 */
@Service
public class QiniuServiceImpl implements QiniuService {
    private static final Logger logger = LogManager.getLogger(QiniuServiceImpl.class);

    private QiniuMapper qiniuMapper;

    @Autowired
    public void setQiniuMapper(QiniuMapper qiniuMapper) {
        this.qiniuMapper = qiniuMapper;
    }

    public String getUploadToken(int courseId) {
        QiniuEntity qiniuEntity = qiniuMapper.getQiniuByCourseId(courseId);
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        return auth.uploadToken(qiniuEntity.getBucket());
    }

    public String getDownloadToken(Auth auth, String domain, String key) {
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(key, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String publicUrl = String.format("%s/%s", domain, encodedFileName);
        return auth.privateDownloadUrl(publicUrl);
    }

    public List<FileVo> getFileList(int courseId, int jobId, int studentId) {
        Configuration cfg = new Configuration(Zone.zone0());
        QiniuEntity qiniuEntity = qiniuMapper.getQiniuByCourseId(courseId);
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        auth.privateDownloadUrl("");
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String prefix = courseId + "/" + jobId + "/" + studentId + "/";
        int limit = 1000;
        String delimiter = "";
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(qiniuEntity.getBucket(), prefix, limit, delimiter);
        FileInfo[] items;
        List<FileVo> fileVos = new ArrayList<FileVo>();
        while (fileListIterator.hasNext()) {
            items = fileListIterator.next();
            for (FileInfo item : items) {
                FileVo fileVo = new FileVo(item);
                String encodedFileName = null;
                try {
                    encodedFileName = URLEncoder.encode(fileVo.getFileName(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String publicUrl = String.format("%s/%s", queryDomain(courseId), encodedFileName);
                String downloadUrl = auth.privateDownloadUrl(publicUrl);
                fileVo.setDownloadUrl(downloadUrl);
                fileVos.add(fileVo);
            }
        }
        return fileVos;
    }

    public String queryDomain(int courseId) {
        Response r = null;
        QiniuEntity qiniuEntity = qiniuMapper.getQiniuByCourseId(courseId);
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        String url = "http://api.qiniu.com/v6/domain/list?tbl=<bucketName>";
        url = url.replace("<bucketName>", qiniuEntity.getBucket());
        Client httpManager = new Client();
        StringMap parameter = auth.authorization(url);
        String domain = null;
        try {
            r = httpManager.get(url, parameter);
            String temp = r.bodyString();
            domain = temp.substring(2, temp.length() - 2);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return "http://" + domain;
    }

    public int delefile(int courseId, String key) {
        int ans = 1;
        Configuration cfg = new Configuration(Zone.zone0());
        QiniuEntity qiniuEntity = qiniuMapper.getQiniuByCourseId(courseId);
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(qiniuEntity.getBucket(), key);
        } catch (QiniuException ex) {
            ans = 0;
        }
        logger.info(ans);
        return ans;
    }

}
