package service;

import dto.CourseDTO;
import dto.UserDTO;
import dto.UserSDTO;
import entity.CourseEntity;
import entity.StudentCourseEntity;
import mapper.CourseMapper;
import mapper.StudentCourseMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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

    @Override
    public int update(CourseEntity courseEntity) {
        return mapper.update(courseEntity);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public int save(CourseEntity courseEntity) {
        return mapper.save(courseEntity);
    }

    @Override
    public CourseEntity selectOne(int id) {
        return mapper.selectOne(id);
    }

    @Override
    public List<CourseEntity> selectAll() {
        return mapper.selectAll();
    }

    @Override
    public CourseDTO selectCourseDTO(int courseId) {
        return mapper.selectCourseDTO(courseId);
    }

    @Override
    public List<CourseDTO> selectCourseDTOListTch(int teacherId) {
        return mapper.selectCourseDTOListTch(teacherId);
    }

    @Override
    public Long selectCourseCountTch(int teacherId) {
        return mapper.selectCourseCountTch(teacherId);
    }

    @Override
    public Set<UserSDTO> selectUserSet(int courseId) {
        return suMapper.selectUserList(courseId);
    }

    @Override
    public Long selectUserCount(int courseId) {
        return suMapper.selectUserCount(courseId);
    }

    @Override
    public List<CourseDTO> selectCourseDTOListStu(int studentId) {
        return suMapper.selectCourseList(studentId);
    }

    @Override
    public Long selectCourseCountStu(int userId) {
        return suMapper.selectCourseCount(userId);
    }

    @Override
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

    @Override
    public Map<String, Integer> saveUserCourse(UserSDTO user, int courseId) {
        return saveUserCourseList(Collections.singleton(user), courseId);
    }

    @Override
    @CacheEvict(cacheNames = "filePrefix",allEntries = true)
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

    @Override
    public UserDTO selectTeacherInfo(int courseId) {
        return mapper.selectTeacherInfo(courseId);
    }
}
