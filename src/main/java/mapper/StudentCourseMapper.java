package mapper;

import dto.CourseDTO;
import dto.UserSDTO;
import entity.StudentCourseEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

/**
 * @author yan
 * @date 2018/11/24 12:40
 * @descripition
 */
@Repository
public interface StudentCourseMapper {

    int delete(@Param("userId") int userId, @Param("courseId") int courseId);

    StudentCourseEntity selectOne(@Param("userId") int userId, @Param("courseId") int courseId);

    int save(StudentCourseEntity entity);

    /**
     * 批量保存学生选课情况
     *
     * @param list
     * @return
     */

    int saveList(List<StudentCourseEntity> list);

    /**
     * 查询该门课程的学生列表
     *
     * @param courseId
     * @return
     */
    Set<UserSDTO> selectUserList(int courseId);

    /**
     * 查询课程选课人数
     *
     * @param courseId
     * @return
     */
    Long selectUserCount(int courseId);

    /**
     * 查询学生选修的课程
     *
     * @param userId
     * @return
     */
    List<CourseDTO> selectCourseList(int userId);

    /**
     * 查询学生选修课程数目
     *
     * @param userId
     * @return
     */
    Long selectCourseCount(int userId);

}
