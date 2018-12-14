package service;

import dto.CourseResourceDTO;
import entity.CourseResourceEntity;

import java.util.List;

public interface ResourceService extends BaseService<CourseResourceEntity> {
    /**
     * 获取课程资源列表
     *
     * @param courseId
     * @return
     */
    List<CourseResourceEntity> selectCourseResource(int courseId);

    /**
     * 获取课程资源文件详情
     *
     * @param id
     * @return
     */
    CourseResourceDTO getCourseResourceDTO(int id);

}
