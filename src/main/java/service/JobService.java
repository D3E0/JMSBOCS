package service;

import dto.JobItemDTO;
import dto.JobSubmitRecordDTO;
import entity.JobEntity;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 11:31
 * @descripition
 */
public interface JobService {
    List<JobItemDTO> findJobListById(int userId,int page,String keyword);
    JobItemDTO findJobById(int jobId);
    void jobItemSubmit(int jobId,int userId,String fileName);
    int countJob(int userId,String keyword);
    int deleteJob(int jobId);
    int updateJob(JobEntity jobEntity);
    int addJob(JobEntity jobEntity);
    List<JobSubmitRecordDTO> getJobSubmitRecord(int jobId,int cur,String keyword,int limit);
    int countJobSubmitRecord(int jobId,String keyword);
}
