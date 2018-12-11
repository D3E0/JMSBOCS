package mapper;

import dto.CourseDTO;
import entity.CourseEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 12:40
 * @descripition
 */
@Repository
public interface CourseMapper extends BaseMapper<CourseEntity> {

    /**
     * 查询课程详情
     *
     * @param courseId
     * @return
     */
    CourseDTO selectCourseDTO(int courseId);

    /**
     * 查询教师开设课程列表
     *
     * @param teacherId
     * @return
     */
    List<CourseDTO> selectCourseDTOListTch(int teacherId);

    /**
     * 查询教师开设课程门数
     *
     * @param teacherId
     * @return
     */
    Long selectCourseCountTch(int teacherId);

}
