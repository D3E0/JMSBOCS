package service;

import dto.CourseAnnouncementDTO;
import entity.AnnouncementEntity;
import entity.NotifyEntity;
import entity.NotifyType;
import manager.NotifyManager;
import mapper.AnnouncementMapper;
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
public class AnnouncementServiceImpl implements AnnouncementService {
    private final AnnouncementMapper mapper;
    private final NotifyManager manager;
    private static final Logger logger = LogManager.getLogger(AnnouncementServiceImpl.class);

    @Autowired
    public AnnouncementServiceImpl(AnnouncementMapper mapper, NotifyManager manager) {
        this.mapper = mapper;
        this.manager = manager;
    }

    @Override
    public int update(AnnouncementEntity announcementEntity) {
        return mapper.update(announcementEntity);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public int save(AnnouncementEntity announcementEntity) {
        int res = mapper.save(announcementEntity);
        if (res > 0) {
            NotifyEntity notify = notifyTemplate(announcementEntity.getAnnouncementId());
            boolean result = manager.add(notify);
            logger.info(String.format("save notify %s ==> %s", notify, result));
        }
        return res;
    }

    @Override
    public AnnouncementEntity selectOne(int id) {
        return mapper.selectOne(id);
    }

    @Override
    public List<AnnouncementEntity> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public List<AnnouncementEntity> selectCourseAnnouncement(int courseId) {
        return mapper.selectCourseAnnouncement(courseId);
    }

    @Override
    public List<CourseAnnouncementDTO> selectTchAnnouncement(int userId) {
        return mapper.selectTchAnnouncement(userId);
    }

    @Override
    public List<CourseAnnouncementDTO> selectStuAnnouncement(int userId) {
        return mapper.selectStuAnnouncement(userId);
    }

    private NotifyEntity notifyTemplate(Integer id) {
        CourseAnnouncementDTO dto = selectCourseAnnouncementDTO(id);
        String content = String.format("教师 %s 在课程 %s 发布新的公告了，快去查看吧", dto.getTeacherName(), dto.getCourseName());
        String title = "课程公告发布提醒";
        return new NotifyEntity(content, title, NotifyType.COURSEWIDE, new Date(), dto.getCourseId());
    }

    private CourseAnnouncementDTO selectCourseAnnouncementDTO(int id) {
        return mapper.selectCourseAnnouncementDTO(id);
    }

}
