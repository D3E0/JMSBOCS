package service;

import dto.CourseResourceDTO;
import entity.CourseResourceEntity;
import mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceMapper mapper;
    private FileService fileService;

    @Autowired
    public ResourceServiceImpl(ResourceMapper mapper) {
        this.mapper = mapper;
    }

    public List<CourseResourceEntity> selectCourseResource(int courseId) {
        return mapper.selectCourseResource(courseId);
    }

    public CourseResourceDTO getCourseResourceDTO(int id) {
        return mapper.selectCourseResourceDTO(id);
    }

    public int update(CourseResourceEntity courseResourceEntity) {
        return mapper.update(courseResourceEntity);
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

    public int save(CourseResourceEntity courseResourceEntity) {
        return mapper.save(courseResourceEntity);
    }

    public CourseResourceEntity selectOne(int id) {
        return mapper.selectOne(id);
    }

    public List<CourseResourceEntity> selectAll() {
        return mapper.selectAll();
    }
}
