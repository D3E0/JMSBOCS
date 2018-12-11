package service;

import dto.CourseDTO;
import dto.UserSDTO;
import entity.CourseEntity;

import java.util.List;
import java.util.Set;

/**
 * @author yan
 * @date 2018/11/24 13:19
 * @descripition
 */
public interface CourseService extends BaseService<CourseEntity> {
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


    /**
     * 查询该门课程的学生列表
     *
     * @param courseId
     * @return
     */
    Set<UserSDTO> selectUserSet(int courseId);

    /**
     * 查询课程选修人数
     *
     * @param courseId
     * @return
     */
    Long selectUserCount(int courseId);

    /**
     * 获取学生选课列表
     *
     * @param studentId
     * @return
     */
    List<CourseDTO> selectCourseDTOListStu(int studentId);

    /**
     * 查询学生选修课程门数
     *
     * @param userId
     * @return
     */
    Long selectCourseCountStu(int userId);


    /**
     * 批量保存学生选课情况 若没有学生，则保存学生信息
     *
     * @param set
     * @param courseId
     * @return
     */
    int saveUserCourseList(Set<UserSDTO> set, int courseId);

    /**
     * 保存单个学生选课情况 若没有学生，则保存学生信息
     *
     * @param user
     * @param courseId
     * @return
     */
    int saveUserCourse(UserSDTO user, int courseId);

    /**
     * 批量删除学生选课情况
     *
     * @param set
     * @param courseId
     * @return
     */
    int deleteUserCourseList(Set<UserSDTO> set, int courseId);


}
