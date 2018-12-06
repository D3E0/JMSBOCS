package util;

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
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import vo.FileVo;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author yan
 * @date 2018/12/5 17:25
 * @descripition
 */
public class QiniuUtil {
    private static final Logger logger = LogManager.getLogger(QiniuUtil.class);
    public static String queryDomain(QiniuEntity qiniuEntity) {
        Response r = null;
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        String url = "http://api.qiniu.com/v6/domain/list?tbl=<bucketName>";
        url = url.replace("<bucketName>", qiniuEntity.getBucket());
        Client httpManager = new Client();
        StringMap parameter = auth.authorization(url);
        System.out.println(parameter.get("Authorization"));
        System.out.println(url);
        String domain=null;
        try {
            r = httpManager.get(url, parameter);
            String temp=r.bodyString();
            domain=temp.substring(2,temp.length()-2);
        } catch (QiniuException e) {
            e.printStackTrace();
        }
        return "http://"+domain;
    }
    public static int delefile(QiniuEntity qiniuEntity,String key) {
        int ans=1;
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        BucketManager bucketManager = new BucketManager(auth, cfg);
        try {
            bucketManager.delete(qiniuEntity.getBucket(), key);
        } catch (QiniuException ex) {
            ans=0;
        }
        return ans;
    }
    public static String getUploadToken(QiniuEntity qiniuEntity) {
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        return auth.uploadToken(qiniuEntity.getBucket());
    }
    public String getDownloadToken(Auth auth,String domain,String key) {
        String encodedFileName = null;
        try {
            encodedFileName = URLEncoder.encode(key, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String publicUrl = String.format("%s/%s", domain, encodedFileName);
        return auth.privateDownloadUrl(publicUrl);
    }
    public static List<FileVo> getFileList( QiniuEntity qiniuEntity,String prefix) {
        Configuration cfg = new Configuration(Zone.zone0());
        Auth auth = Auth.create(qiniuEntity.getAk(), qiniuEntity.getSk());
        auth.privateDownloadUrl("");
        BucketManager bucketManager = new BucketManager(auth, cfg);
        int limit = 1000;
        String delimiter ="";
        BucketManager.FileListIterator fileListIterator = bucketManager.createFileListIterator(qiniuEntity.getBucket(), prefix, limit, delimiter);
        FileInfo[] items;
        List<FileVo> fileVos=new ArrayList<FileVo>();
        while (fileListIterator.hasNext()) {
            items = fileListIterator.next();
            for (FileInfo item : items) {
                FileVo fileVo=new FileVo(item);
                String encodedFileName = null;
                try {
                    encodedFileName = URLEncoder.encode(fileVo.getFileName(), "utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                String publicUrl = String.format("%s/%s", queryDomain(qiniuEntity), encodedFileName);
                String downloadUrl=auth.privateDownloadUrl(publicUrl);
                fileVo.setDownloadUrl(downloadUrl);
                fileVos.add(fileVo);
            }
        }
        return fileVos;
    }
}
