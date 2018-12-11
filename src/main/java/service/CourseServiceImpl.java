package service;

import dto.CourseDTO;
import dto.UserSDTO;
import entity.CourseEntity;
import entity.StudentCourseEntity;
import mapper.CourseMapper;
import mapper.StudentCourseMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

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

    private static final Logger logger = LogManager.getLogger(CourseServiceImpl.class);

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

    public int saveUserCourseList(Set<UserSDTO> set, int courseId) {
        int res = userService.saveStuSet(set);
        logger.info("add " + res + " user to database");
        List<StudentCourseEntity> entityList = new ArrayList<StudentCourseEntity>();
        deleteUserCourseList(set, courseId);
        for (UserSDTO userSDTO : set) {
            entityList.add(new StudentCourseEntity(userSDTO.getUserId(), courseId));
        }
        res = suMapper.saveListIgnore(entityList);
        logger.info("add " + res + " user to course " + courseId);
        return res;
    }

    public int saveUserCourse(UserSDTO user, int courseId) {
        return saveUserCourseList(Collections.singleton(user), courseId);
    }

    public int deleteUserCourseList(Set<UserSDTO> set, int courseId) {
        Set<UserSDTO> existStudents = selectUserSet(courseId);
        existStudents.removeAll(set);
        List<StudentCourseEntity> toDelete = new ArrayList<StudentCourseEntity>();
        logger.info(existStudents);
        for (UserSDTO sdto : existStudents) {
            toDelete.add(new StudentCourseEntity(sdto.getUserId(), courseId));
        }
        int res = 0;
        if (toDelete.size() > 0) {
            res = suMapper.deleteList(toDelete);
        }
        logger.info("delete " + res + " user under course " + courseId);
        return res;
    }
}
