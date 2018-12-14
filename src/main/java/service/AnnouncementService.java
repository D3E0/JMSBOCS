package service;

import dto.CourseAnnouncementDTO;
import entity.AnnouncementEntity;

import java.util.List;

public interface AnnouncementService extends BaseService<AnnouncementEntity> {
    /**
     * 返回课程公告
     *
     * @param courseId
     * @return
     */
    List<AnnouncementEntity> selectCourseAnnouncement(int courseId);

    /**
     * 返回教师所有开设课程的公告
     *
     * @param userId
     * @return
     */
    List<CourseAnnouncementDTO> selectTchAnnouncement(int userId);

    /**
     * 返回学生所有选修课程的公告
     *
     * @param userId
     * @return
     */
    List<CourseAnnouncementDTO> selectStuAnnouncement(int userId);
}
