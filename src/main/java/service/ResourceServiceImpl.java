package service;

import dto.CourseResourceDTO;
import entity.CourseResourceEntity;
import entity.NotifyEntity;
import entity.NotifyType;
import manager.NotifyManager;
import mapper.ResourceMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ACM-PC
 */
@Service
public class ResourceServiceImpl implements ResourceService {
    private static final Logger logger = LogManager.getLogger(ResourceServiceImpl.class);

    private final ResourceMapper mapper;
    private final NotifyManager manager;

    @Autowired
    public ResourceServiceImpl(ResourceMapper mapper, NotifyManager manager) {
        this.mapper = mapper;
        this.manager = manager;
    }

    @Override
    public List<CourseResourceEntity> selectCourseResource(int courseId) {
        return mapper.selectCourseResource(courseId);
    }

    @Override
    public CourseResourceDTO getCourseResourceDTO(int id) {
        return mapper.selectCourseResourceDTO(id);
    }

    @Override
    public int update(CourseResourceEntity courseResourceEntity) {
        return mapper.update(courseResourceEntity);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public int save(CourseResourceEntity entity) {
        CourseResourceEntity exist = mapper.selectExistCourseResource(entity.getCourseId(), entity.getCourseResourceFilename());
        if (exist != null) {
            logger.info("already exist resource " + entity.getCourseId() + " " + entity.getCourseResourceFilename());
            return 0;
        }
        int res = mapper.save(entity);
        if (res > 0) {
            NotifyEntity notify = notifyTemplate(entity.getCourseResourceId());
            boolean result  = manager.add(notify);
            logger.info(String.format("save notify %s ==> %s", notify, result));

        }
        return res;
    }

    @Override
    public CourseResourceEntity selectOne(int id) {
        return mapper.selectOne(id);
    }

    @Override
    public List<CourseResourceEntity> selectAll() {
        return mapper.selectAll();
    }

    private NotifyEntity notifyTemplate(Integer id) {
        CourseResourceDTO dto = getCourseResourceDTO(id);
        String content = String.format("教师在课程 %s 上传了新的教学资源，快去下载吧", dto.getCourseName());
        String title = "课程资源发布提醒";
        return new NotifyEntity(content, title, NotifyType.COURSEWIDE, new Date(), dto.getCourseId());
    }
}
