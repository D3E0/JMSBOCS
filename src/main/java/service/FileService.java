package service;

import vo.FileVO;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/5 17:28
 * @descripition
 */
public interface FileService {
    String getUploadToken(int courseId);
    List<FileVO> getFileList(int courseId, int jobId, int studentId);
    List<FileVO> getPublicFileList(int courseId);
    String queryDomain(int courseId);
    int delefile(int courseId,String key);
}
