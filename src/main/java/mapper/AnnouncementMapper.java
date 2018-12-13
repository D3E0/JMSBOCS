package mapper;

import entity.AnnouncementEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACM-PC
 */
@Repository
public interface AnnouncementMapper extends BaseMapper<AnnouncementEntity> {

    List<AnnouncementEntity> selectCourseAnnouncement(int courseId);
}
