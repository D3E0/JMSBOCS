package service;

import dto.JobSubmitRecordDTO;
import dto.JobSubmitRecordNumber;
import mapper.JobSubmitItemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/11 17:08
 * @descripition
 */
@Service
public class JobSubmitServiceImpl implements JobSubmitService{
    private JobSubmitItemMapper jobSubmitItemMapper;
    @Autowired
    public void setJobSubmitItemMapper(JobSubmitItemMapper jobSubmitItemMapper) {
        this.jobSubmitItemMapper = jobSubmitItemMapper;
    }
    public List<JobSubmitRecordDTO> getJobSubmitRecord(int jobId, int cur, String keyword, int limit) {
        return jobSubmitItemMapper.getJobSubmitRecord(jobId, (cur-1)*limit, keyword,limit);
    }

    public int countJobSubmitRecord(int jobId, String keyword) {
        return jobSubmitItemMapper.countJobSubmitRecord(jobId,keyword);
    }

    public JobSubmitRecordNumber countJobSubmitRecordNum(int jobId) {
        return jobSubmitItemMapper.countJobSubmitRecordNum(jobId);
    }
    @Caching(evict = {
            @CacheEvict(cacheNames = "fileList", key = "'j'+#jobId+'u'+#userId"),
            @CacheEvict(cacheNames = "allFile", key = "#jobId")
    })
    public void jobItemSubmit(int jobId, int userId, String fileName) {
        if (jobSubmitItemMapper.isSameFile(jobId,fileName,userId)==0) {
            jobSubmitItemMapper.jobItemSubmit(jobId,fileName,userId);
        }
    }
}
