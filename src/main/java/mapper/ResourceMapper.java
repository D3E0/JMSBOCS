package mapper;

import entity.AnnouncementEntity;
import entity.CourseResourceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACM-PC
 */
@Repository
public interface ResourceMapper extends BaseMapper<CourseResourceEntity> {

    List<CourseResourceEntity> selectCourseResource(int courseId);
}
