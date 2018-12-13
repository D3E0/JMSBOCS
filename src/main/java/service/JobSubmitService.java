package service;

import dto.JobSubmitRecordDTO;
import dto.JobSubmitRecordNumber;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/11 17:07
 * @descripition
 */
public interface JobSubmitService {
    List<JobSubmitRecordDTO> getJobSubmitRecord(int jobId, int cur, String keyword, int limit);
    int countJobSubmitRecord(int jobId,String keyword);
    JobSubmitRecordNumber countJobSubmitRecordNum(@Param("jobId")int jobId);
    void jobItemSubmit(int jobId,int userId,String fileName);
}
