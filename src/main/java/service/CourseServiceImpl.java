package service;

import dto.CourseDTO;
import dto.UserSDTO;
import entity.CourseEntity;
import entity.StudentCourseEntity;
import mapper.CourseMapper;
import mapper.StudentCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * @author yan
 * @date 2018/11/24 13:20
 * @descripition
 */
@Service
public class CourseServiceImpl implements CourseService {
    private final CourseMapper mapper;
    private final StudentCourseMapper suMapper;
    private final UserService userService;

    @Autowired
    public CourseServiceImpl(CourseMapper mapper, StudentCourseMapper suMapper, UserService userService) {
        this.mapper = mapper;
        this.suMapper = suMapper;
        this.userService = userService;
    }

    public int update(CourseEntity courseEntity) {
        return mapper.update(courseEntity);
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

    public int save(CourseEntity courseEntity) {
        return mapper.save(courseEntity);
    }

    public CourseEntity selectOne(int id) {
        return mapper.selectOne(id);
    }

    public List<CourseEntity> selectAll() {
        return mapper.selectAll();
    }

    public CourseDTO selectCourseDTO(int courseId) {
        return mapper.selectCourseDTO(courseId);
    }

    public List<CourseDTO> selectCourseDTOListTch(int teacherId) {
        return mapper.selectCourseDTOListTch(teacherId);
    }

    public Long selectCourseCountTch(int teacherId) {
        return mapper.selectCourseCountTch(teacherId);
    }

    public Set<UserSDTO> selectUserSet(int courseId) {
        return suMapper.selectUserList(courseId);
    }

    public Long selectUserCount(int courseId) {
        return suMapper.selectUserCount(courseId);
    }

    public List<CourseDTO> selectCourseDTOListStu(int studentId) {
        return suMapper.selectCourseList(studentId);
    }

    public Long selectCourseCountStu(int userId) {
        return suMapper.selectCourseCount(userId);
    }

    public int saveUserCourseList(List<UserSDTO> list, int courseId) {
        int res = 0;
        Set<UserSDTO> existSet = this.selectUserSet(courseId);
        Set<UserSDTO> rawSet = new HashSet<UserSDTO>(list);
        rawSet.removeAll(existSet);
        res = userService.saveStuSet(rawSet);
        List<StudentCourseEntity> entityList = new ArrayList<StudentCourseEntity>();
        for (UserSDTO userSDTO : list) {
            entityList.add(new StudentCourseEntity(userSDTO.getUserId(), courseId));
        }
        res = suMapper.saveList(entityList);
        return res;
    }

    public int saveUserCourse(UserSDTO user, int courseId) {
        int res = 0;
        res = userService.saveStuSet(Collections.singleton(user));
        res = suMapper.save(new StudentCourseEntity(user.getUserId(), courseId));
        return res;
    }
}
