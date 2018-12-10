package service;

import dto.CourseItemDto;
import entity.CourseEntity;
import entity.UserEntity;
import entity.UserType;
import mapper.CourseMapper;
import mapper.UserMapper;
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
    private UserMapper userMapper;

    @Autowired
    public CourseServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Autowired
    public void setCourseMapper(CourseMapper courseMapper) {
        this.courseMapper = courseMapper;
    }

    public List<CourseItemDto> findCourseListById(int userId, int page, String keyword) {
        UserEntity userEntity=userMapper.selectOne(userId);
        if (userEntity.getType()== UserType.STUDENT) {
            return courseMapper.findCourseListForStudent(userId, (page - 1) * 10, keyword);
        }
        else {
            return courseMapper.findCourseListForTeacher(userId, (page - 1) * 10, keyword);
        }
    }

    public int countCourseById(int userId, String keyword) {
        UserEntity userEntity=userMapper.selectOne(userId);
        if (userEntity.getType()== UserType.STUDENT) {
            return courseMapper.countCourseForStudent(userId, keyword);
        }
        else {
            return courseMapper.countCourseForTeacher(userId, keyword);
        }
    }


    public int updateCourse(CourseEntity t) {
        return courseMapper.update(t);
    }


    public int deleteCourse(int id) {
        return courseMapper.delete(id);
    }


    public int saveCourse(CourseEntity t) {
        return courseMapper.save(t);
    }
}
