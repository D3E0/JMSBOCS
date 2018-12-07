package service;

import dto.CourseItemDto;
import entity.CourseEntity;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 13:19
 * @descripition
 */
public interface CourseService {
    List<CourseItemDto> findCourseListById(int studenId,int page,String keyword);
    int countCourseById(int studentId,String keyword);
    int updateCourse(CourseEntity t);

    int deleteCourse(int id);

    int saveCourse(CourseEntity t);
}
