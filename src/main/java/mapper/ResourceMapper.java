package mapper;

import dto.CourseResourceDTO;
import entity.CourseResourceEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACM-PC
 */
@Repository
public interface ResourceMapper extends BaseMapper<CourseResourceEntity> {

    List<CourseResourceEntity> selectCourseResource(int courseId);

    CourseResourceDTO selectCourseResourceDTO(int id);
}
