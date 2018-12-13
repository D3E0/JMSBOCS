package service;

import entity.CourseResourceEntity;
import mapper.ResourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.ServiceMode;
import java.util.List;

@Service
public class ResourceServiceImpl implements ResourceService {
    private final ResourceMapper mapper;

    @Autowired
    public ResourceServiceImpl(ResourceMapper mapper) {
        this.mapper = mapper;
    }

    public List<CourseResourceEntity> selectCourseResource(int courseId) {
        return mapper.selectCourseResource(courseId);
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
