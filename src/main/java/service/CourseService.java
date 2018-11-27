package service;

import dto.CourseItemDto;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 13:19
 * @descripition
 */
public interface CourseService {
    List<CourseItemDto> findCourseListById(int studenId,int page);
}
