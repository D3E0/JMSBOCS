package mapper;

import dto.CourseItemDto;
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
    List<CourseItemDto> findCourseListById(@Param("studentId") int studentId, @Param("cur")int cur);
}
