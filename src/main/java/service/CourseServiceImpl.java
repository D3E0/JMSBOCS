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

    public Map<String, Integer> saveUserCourseList(Set<UserSDTO> set, int courseId) {
        Map<String, Integer> result = new HashMap<String, Integer>(3);
        int res = userService.saveStuSet(set);
        result.put("saveUser", res);
        res = deleteUserCourseList(set, courseId);
        result.put("deleteStu", res);
        List<StudentCourseEntity> entityList = new ArrayList<StudentCourseEntity>();
        for (UserSDTO userSDTO : set) {
            entityList.add(new StudentCourseEntity(userSDTO.getUserId(), courseId));
        }
        logger.info(String.format("to add %d stu to course %d ==> %s", entityList.size(), courseId, entityList));
        res = 0;
        if (entityList.size() > 0) {
            res = suMapper.saveListIgnore(entityList);
        }
        result.put("addStu", res);
        logger.info("add " + res + " stu to course " + courseId);
        return result;
    }

    public Map<String, Integer> saveUserCourse(UserSDTO user, int courseId) {
        return saveUserCourseList(Collections.singleton(user), courseId);
    }

    public int deleteUserCourseList(Set<UserSDTO> set, int courseId) {
        Set<UserSDTO> existStudents = selectUserSet(courseId);
        logger.info(String.format("exist %d students under course %d ==> %s",
                existStudents.size(), courseId, existStudents));
        existStudents.removeAll(set);
        logger.info(String.format("to remove %d students from course %d ==> %s",
                existStudents.size(), courseId, existStudents));
        if (existStudents.size() == 0) {
            return 0;
        }
        List<StudentCourseEntity> toDelete = new ArrayList<StudentCourseEntity>();
        for (UserSDTO sdto : existStudents) {
            toDelete.add(new StudentCourseEntity(sdto.getUserId(), courseId));
        }
        int res = suMapper.deleteList(toDelete);
        logger.info("remove " + res + " students from course " + courseId);
        return res;
    }
}
