package mapper;

import dto.CourseItemDTO;
import entity.CourseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 12:40
 * @descripition
 */
@Component
public interface CourseMapper extends BaseMapper<CourseEntity>{
    int countCourseById(@Param("studentId") int studentId, @Param("keyword")String keyword);
    List<CourseItemDTO> findCourseListById(@Param("studentId") int studentId, @Param("cur")int cur, @Param("keyword")String keyword);
}
