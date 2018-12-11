package controller;

import dto.CourseDTO;
import dto.UserSDTO;
import entity.CourseEntity;
import entity.UserEntity;
import entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CourseService;
import service.UserService;
import util.RestResult;
import vo.CourseVO;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Set;

/**
 * @author yan
 * @date 2018/11/25 14:51
 * @descripition
 */
@Controller
public class CourseController {
    private final CourseService service;
    private final UserService userService;

    private static final Logger logger = LogManager.getLogger(CourseController.class);

    @Autowired
    public CourseController(UserService userService, CourseService service) {
        this.userService = userService;
        this.service = service;
    }

    /**
     * 教师开设新的课程
     *
     * @param name
     * @param description
     * @param id
     * @param academic
     * @param semester
     * @param response
     * @return
     */
    @RequestMapping(value = "/api/subject", method = RequestMethod.POST)
    @ResponseBody
    public RestResult createSubject(@RequestParam() String name,
                                    @RequestParam() String description,
                                    @RequestParam() Integer id,
                                    @RequestParam() String academic,
                                    @RequestParam() String semester,
                                    HttpServletResponse response) {
        CourseEntity entity = new CourseEntity(name, id, academic, description, semester);
        int res = service.save(entity);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(500).message("fail").build();
        }
    }

    /**
     * 根据课程 ID 删除课程
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/subject", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult deleteSubject(@RequestParam Integer id) {
        int res = service.delete(id);
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(500).message("fail").build();
        }
    }

    /**
     * 获取课程详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/subject", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getSubject(@RequestParam Integer id) {
        CourseDTO courseDTO = service.selectCourseDTO(id);
        return new RestResult.Builder(200).data(courseDTO).build();
    }

    /**
     * 根据用户类型返回相应的课程列表  选课列表或开课列表
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/api/subjectList", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getSubjectList(@RequestParam Integer userId) {
        logger.info("user query subject list ==> " + userId);
        UserEntity entity = userService.selectOne(userId);
        List<CourseDTO> list;
        if (UserType.TEACHER.equals(entity.getType())) {
            list = service.selectCourseDTOListTch(entity.getUserId());
        } else {
            list = service.selectCourseDTOListStu(entity.getUserId());
        }
        logger.info(list);
        return new RestResult.Builder(200).data(list).build();
    }

    @RequestMapping(value = "/api/subject/user", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getCourseStudents(@RequestParam Integer courseId) {
        logger.info("course query student ==> " + courseId);
        Set<UserSDTO> set = service.selectUserSet(courseId);
        Long count = service.selectUserCount(courseId);
        logger.info("count ==> " + count);
        logger.info(set);
        return new RestResult.Builder(200).count(count).data(set).build();
    }

    @RequestMapping(value = "/api/subject/user", method = RequestMethod.POST)
    @ResponseBody
    public RestResult saveCourseStudents(@RequestBody CourseVO courseVO, HttpServletResponse response) {
        logger.info("course add student ==> " + courseVO);
        int res = service.saveUserCourseList(courseVO.getStudentList(), courseVO.getCourseId());
        logger.info("add " + res + " user to course " + courseVO.getCourseId());
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(200).message("fail").build();
        }
    }
}
