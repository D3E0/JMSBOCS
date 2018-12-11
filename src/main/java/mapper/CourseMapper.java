package mapper;
import dto.CourseItemDTO;
import entity.CourseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 12:40
 * @descripition
 */
@Repository
public interface CourseMapper extends BaseMapper<CourseEntity>{
    int countCourseForStudent(@Param("studentId") int studentId, @Param("keyword")String keyword);
    List<CourseItemDTO> findCourseListForStudent(@Param("studentId") int studentId, @Param("cur")int cur, @Param("keyword")String keyword);
    int countCourseForTeacher(@Param("teacherId") int teacherId, @Param("keyword")String keyword);
    List<CourseItemDTO> findCourseListForTeacher(@Param("teacherId") int teacherId, @Param("cur")int cur, @Param("keyword")String keyword);
}
