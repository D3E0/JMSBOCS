package service;

import dto.CourseItemDTO;
import entity.CourseEntity;
import mapper.CourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 13:20
 * @descripition
 */
@Service
public class CourseServiceImpl implements CourseService {
    private CourseMapper courseMapper;
    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    @Override
    public List<CourseItemDTO> findCourseListById(int studenId, int page, String keyword) {
        return courseMapper.findCourseListById(studenId,(page-1)*10,keyword);
    }

    @Override
    public int countCourseById(int studentId, String keyword) {
        return courseMapper.countCourseById(studentId,keyword);
    }

    @Override
    public int updateCourse(CourseEntity t) {
        return courseMapper.update(t);
    }

    @Override
    public int deleteCourse(int id) {
        return courseMapper.delete(id);
    }

    @Override
    public int saveCourse(CourseEntity t) {
        return courseMapper.save(t);
    }
}
