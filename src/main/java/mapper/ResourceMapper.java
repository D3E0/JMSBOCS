package mapper;

import dto.CourseResourceDTO;
import entity.CourseResourceEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author ACM-PC
 */
@Repository
public interface ResourceMapper extends BaseMapper<CourseResourceEntity> {

    List<CourseResourceEntity> selectCourseResource(int courseId);

    CourseResourceDTO selectCourseResourceDTO(int id);

    CourseResourceEntity selectExistCourseResource(@Param("courseId") int courseId, @Param("filename") String filename);
}
