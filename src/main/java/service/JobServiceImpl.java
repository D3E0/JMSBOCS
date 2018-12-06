package service;

import dto.JobItemDTO;
import entity.JobEntity;
import mapper.JobMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 11:32
 * @descripition
 */
@Service
public class JobServiceImpl implements JobService {
    private static final Logger logger = LogManager.getLogger(JobServiceImpl.class);

    private JobMapper jobMapper;

    @Autowired
    public void setJobMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Override
    public List<JobItemDTO> findJobListById(int studentId, int page, String keyword) {
        return jobMapper.findJobListByIdAndKeyword(studentId, (page - 1) * 10, keyword);
    }

    @Override
    public JobItemDTO findJobById(int jobId) {
        return jobMapper.findJobById(jobId);
    }

    @Override
    public int countJob(int studentId, String keyword) {
        return jobMapper.countJobByKeyword(studentId, keyword);
    }

    @Override
    public void deleteJob(int jobId) {
        jobMapper.delete(jobId);
    }

    @Override
    public void updateJob(JobEntity jobEntity) {
        jobMapper.update(jobEntity);
    }

    @Override
    public void addJob(JobEntity jobEntity) {
        jobMapper.save(jobEntity);
    }

}
