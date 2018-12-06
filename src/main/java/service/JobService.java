package service;

import dto.JobItemDTO;
import entity.JobEntity;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 11:31
 * @descripition
 */
public interface JobService {
    List<JobItemDTO> findJobListById(int studentId,int page,String keyword);
    JobItemDTO findJobById(int jobId);
    int countJob(int studentId,String keyword);
    void deleteJob(int jobId);
    void updateJob(JobEntity jobEntity);
    void addJob(JobEntity jobEntity);
}
