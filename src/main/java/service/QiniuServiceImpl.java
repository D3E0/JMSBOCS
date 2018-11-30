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

/**
 * @author yan
 * @date 2018/11/30 16:43
 * @descripition
 */
@Service
public class QiniuServiceImpl implements QiniuService {
    private static final Logger logger = LogManager.getLogger(JobServiceImpl.class);

    private QiniuMapper qiniuMapper;
    @Autowired
    public void setQiniuMapper(QiniuMapper qiniuMapper) {
        this.qiniuMapper = qiniuMapper;
    }
    @Override
    public String getUploadToken(int courseId) {
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        return auth.uploadToken(qiniuEntity.getBucket());
    }

    @Override
    public String getDownloadToken(int courseId) {
        Response r = null;
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        return auth.privateDownloadUrl("");
    }

    @Override
    public FileInfo[] getFileList(int courseId, int jobId, int studentId) {

        Configuration cfg = new Configuration(Zone.zone0());
        //...其他参数参考类注释
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        String prefix = courseId+"/"+jobId+"/"+studentId+"/";
        int limit = 1000;
        String delimiter ="";
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(qiniuEntity.getBucket(), prefix, limit, delimiter);
        FileInfo[] items = new FileInfo[0];
        while (fileListIterator.hasNext()) {
            items = fileListIterator.next();
        }
        return items;
    }

    @Override
    public String queryDomain(int courseId) {
        Response r = null;
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        String url = "http://api.qiniu.com/v6/domain/list?tbl=<bucketName>";
        url = url.replace("<bucketName>", qiniuEntity.getBucket());
        Client httpManager = new Client();
        StringMap parameter = auth.authorization(url);
        String domain=null;
        try {
            r = httpManager.get(url, parameter);
            String temp=r.bodyString();
            domain=temp.substring(2,temp.length()-2);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return domain;
    }
}
