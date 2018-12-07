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
    int deleteJob(int jobId);
    int updateJob(JobEntity jobEntity);
    int addJob(JobEntity jobEntity);
}
