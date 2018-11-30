package service;

import com.qiniu.storage.model.FileInfo;

/**
 * @author yan
 * @date 2018/11/30 16:43
 * @descripition
 */
public interface QiniuService {
    String getUploadToken(int courseId);
    String getDownloadToken(int courseId);
    FileInfo[] getFileList(int courseId, int jobId, int studentId);
    String queryDomain(int courseId);
}
