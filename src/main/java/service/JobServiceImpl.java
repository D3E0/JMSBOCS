package service;

import dto.JobItemDTO;
import entity.JobEntity;
import entity.QiniuEntity;
import entity.UserEntity;
import entity.UserType;
import mapper.JobMapper;
import mapper.QiniuMapper;
import mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import util.QiniuUtil;
import vo.FileVO;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 11:32
 * @descripition
 */
@Service
public class JobServiceImpl implements JobService {
    private static final Logger logger = LogManager.getLogger(JobServiceImpl.class);
    private final UserMapper userMapper;
    private JobMapper jobMapper;
    private QiniuMapper qiniuMapper;
    @Autowired
    public void setQiniuMapper(QiniuMapper qiniuMapper) {
        this.qiniuMapper = qiniuMapper;
    }

    @Autowired
    public JobServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setJobMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    public List<JobItemDTO> findJobListById(int userId, int page, String keyword) {
        UserEntity userEntity=userMapper.selectOne(userId);
        if (userEntity.getType()== UserType.STUDENT) {
            return jobMapper.findJobListForStudent(userId,(page-1)*10, keyword);
        }
        else {
            return jobMapper.findJobListForTeacher(userId,(page-1)*10, keyword);
        }
    }

    public JobItemDTO findJobById(int jobId) {
        return jobMapper.findJobById(jobId);
    }

    public int countJob(int userId, String keyword) {
        logger.info("userId"+userId+"\n"+"keyword"+keyword);
        UserEntity userEntity=userMapper.selectOne(userId);
        if (userEntity.getType()== UserType.STUDENT) {
            logger.info("\nstudent");
            return jobMapper.countJobForStudent(userId, keyword);
        }
        else {
            logger.info("\nteacher");
            return jobMapper.countJobForTeacher(userId, keyword);
        }
    }
    @Caching(evict = {
            @CacheEvict(cacheNames = "filePrefix",key = "#jobId"),
            @CacheEvict(cacheNames = "allFile",key = "#jobId"),
            @CacheEvict(cacheNames = "fileList",allEntries = true)})
    public int deleteJob(int jobId) {
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(jobMapper.findJobById(jobId).getCourseId());
        QiniuUtil.deleteFileList(qiniuEntity,jobMapper.findFilePrefixByJobId(jobId).getFilePrefix());
        return jobMapper.delete(jobId);
    }
    
    public int updateJob(JobEntity jobEntity) {
        int ans=0;
        JobEntity originJobEntity=jobMapper.selectOne(jobEntity.getJobId());
        String originTitle=originJobEntity.getJobTitle();
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(originJobEntity.getCourseId());
        ans=jobMapper.update(jobEntity);
        if (ans!=0&&!originTitle.equals(jobEntity.getJobTitle())){
            changeJobFileName(qiniuEntity,jobEntity);
        }
        return ans;
    }

    @Caching(evict = {
            @CacheEvict(cacheNames = "filePrefix",key = "#jobEntity.getJobId()"),
            @CacheEvict(cacheNames = "allFile",key = "#jobEntity.getJobId()"),
            @CacheEvict(cacheNames = "fileList",allEntries = true)})
    public void changeJobFileName(QiniuEntity qiniuEntity,JobEntity jobEntity){
        List<FileVO> fileVOS=QiniuUtil.getFileList(qiniuEntity,
                jobMapper.findFilePrefixByJobId(jobEntity.getJobId()).getFilePrefix());
        for (FileVO fileVO:fileVOS) {
            String fromKey=fileVO.getFileName();
            String[] strings=fromKey.split("/");
            String toKey=strings[0]+"/"+jobEntity.getJobTitle()+"/"+strings[2];
            QiniuUtil.fileRename(qiniuEntity,fromKey,toKey);
        }
    }

    public int addJob(JobEntity jobEntity) {
        return jobMapper.save(jobEntity);
    }

    @Override
    public int isSameJobTitle(int courseId, String jobTitle) {
        return jobMapper.isSameJobTitle(courseId, jobTitle);
    }

}
