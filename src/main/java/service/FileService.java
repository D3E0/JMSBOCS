package service;

import dto.JobFilePrefix;
import vo.FileVO;
import vo.FileVOs;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/5 17:28
 * @descripition
 */
public interface FileService {
    String getUploadToken(int courseId);
    List<FileVO> getFileList(int courseId, int jobId, int studentId);
    List<FileVO> getAllPublicFile(int courseId);
    FileVOs getPublicFiles(int courseId,int page);
    String queryDomain(int courseId);
    int deleteFile(int courseId,String key,int jobId);
    JobFilePrefix findFilePrefixByJobId(int jobId);
    List<FileVO> getAllFile(int courseId, int jobId);
}
