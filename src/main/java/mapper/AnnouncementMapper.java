package mapper;

import dto.CourseAnnouncementDTO;
import entity.AnnouncementEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACM-PC
 */
@Repository
public interface AnnouncementMapper extends BaseMapper<AnnouncementEntity> {

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

    /**
     * @param id
     * @return
     */
    CourseAnnouncementDTO selectCourseAnnouncementDTO(int id);
}