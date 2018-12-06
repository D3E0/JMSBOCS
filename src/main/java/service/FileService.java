package service;

import vo.FileVo;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/5 17:28
 * @descripition
 */
public interface FileService {
    String getUploadToken(int courseId);
    List<FileVo> getFileList(int courseId, int jobId, int studentId);
    String queryDomain(int courseId);
    int delefile(int courseId,String key);
}
