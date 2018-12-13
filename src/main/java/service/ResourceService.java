package service;

import entity.CourseResourceEntity;

import java.util.List;

public interface ResourceService extends BaseService<CourseResourceEntity> {
    List<CourseResourceEntity> selectCourseResource(int courseId);

}
