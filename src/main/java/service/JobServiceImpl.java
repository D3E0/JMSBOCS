package service;

import dto.JobItemDTO;
import mapper.JobMapper;
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

    private JobMapper jobMapper;

    @Autowired
    public void setJobMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Override
    public List<JobItemDTO> findJobListById(int studentId, int page, String keyword) {
        return jobMapper.findJobListByIdAndKeyword(studentId, (page - 1) * 5, keyword);
    }

    @Override
    public JobItemDTO findJobById(int jobId) {
        return jobMapper.findJobById(jobId);
    }

    @Override
    public int countJob(int studentId, String keyword) {
        return jobMapper.countJobByKeyword(studentId, keyword);
    }
}
