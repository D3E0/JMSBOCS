package service;

import entity.QiniuEntity;
import mapper.JobMapper;
import mapper.QiniuMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import util.QiniuUtil;
import vo.FileVO;
import vo.FileVOs;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/5 17:28
 * @descripition
 */
@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LogManager.getLogger(FileServiceImpl.class);
    private JobMapper jobMapper;
    private QiniuMapper qiniuMapper;
    @Autowired
    public void setJobMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Autowired
    public void setQiniuMapper(QiniuMapper qiniuMapper) {
        this.qiniuMapper = qiniuMapper;
    }
    @Cacheable(cacheNames = "UploadToken",key ="#courseId")
    public String getUploadToken(int courseId) {
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.getUploadToken(qiniuEntity);
    }

    public List<FileVO> getFileList(int courseId, int jobId, int studentId) {
        String prefix = courseId+"/"+jobId+"/"+studentId+"/";
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.getFileList(qiniuEntity,prefix);
    }

    @Cacheable(cacheNames = "allPublicFile",key ="#courseId")
    public List<FileVO> getAllPublicFile(int courseId) {
        String prefix = "public/"+courseId+"/";
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.getFileList(qiniuEntity,prefix);
    }
    @Cacheable(cacheNames = "publicFileList",key ="#+'c'+courseId+'p'+page")
    public FileVOs getPublicFiles(int courseId,int page) {
        FileVOs fileVOs=new FileVOs();
        List<FileVO> fileVOList=getAllPublicFile(courseId);
        fileVOs.setFileVOList(fileVOList.subList((page-1)*10,
                fileVOList.size()>page*10?page*10:fileVOList.size()));
        fileVOs.setCount(fileVOList.size());
        return fileVOs;
    }

    public String queryDomain(int courseId) {
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.queryDomain(qiniuEntity);
    }

    public int deleteFile(int courseId,String key) {
        String[] strings=key.split("/");
        int jobId=Integer.parseInt(strings[1]);
        int userId=Integer.parseInt(strings[2]);
        String fileName=strings[3];
        logger.info("jobId="+jobId+"===userId="+userId+"====fileName="+fileName);
        jobMapper.jobItemDelete(jobId,fileName,userId);
        logger.info("jobItemDelete");
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.delefile(qiniuEntity,key);
    }
}
