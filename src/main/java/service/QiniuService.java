package service;

import vo.FileVo;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/30 16:43
 * @descripition
 */
public interface QiniuService {
    String getUploadToken(int courseId);
    List<FileVo> getFileList(int courseId, int jobId, int studentId);
    String queryDomain(int courseId);
    int delefile(int courseId,String key);
}
