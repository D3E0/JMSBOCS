package service;

import entity.QiniuEntity;
import mapper.QiniuMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import util.QiniuUtil;
import vo.FileVO;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/5 17:28
 * @descripition
 */
@Service
public class FileServiceImpl implements FileService {
    private static final Logger logger = LogManager.getLogger(QiniuServiceImpl.class);

    private QiniuMapper qiniuMapper;
    @Autowired
    public void setQiniuMapper(QiniuMapper qiniuMapper) {
        this.qiniuMapper = qiniuMapper;
    }
    @Override
    public String getUploadToken(int courseId) {
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.getUploadToken(qiniuEntity);
    }

    @Override
    public List<FileVO> getFileList(int courseId, int jobId, int studentId) {
        String prefix = courseId+"/"+jobId+"/"+studentId+"/";
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.getFileList(qiniuEntity,prefix);
    }

    @Override
    public List<FileVO> getPublicFileList(int courseId) {
        String prefix = "public/"+courseId+"/";
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.getFileList(qiniuEntity,prefix);
    }

    @Override
    public String queryDomain(int courseId) {
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.queryDomain(qiniuEntity);
    }

    @Override
    public int delefile(int courseId,String key) {
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(courseId);
        return QiniuUtil.delefile(qiniuEntity,key);
    }
}
