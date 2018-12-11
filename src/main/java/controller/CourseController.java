package controller;

import dto.CourseDTO;
import entity.CourseEntity;
import entity.UserEntity;
import entity.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CourseService;
import service.UserService;
import util.RestResult;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author yan
 * @date 2018/11/25 14:51
 * @descripition
 */
@Controller
public class CourseController {
    private final CourseService service;
    private final UserService userService;

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
    public RestResult saveSubject(@RequestParam() String name,
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


    @RequestMapping(value = "/api/subject", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getSubject(@RequestParam Integer id) {
        CourseDTO courseDTO = service.selectCourseDTO(id);
        return new RestResult.Builder(200).data(courseDTO).build();
    }

    /**
     * 根据用户类型返回相应的课程列表  选课列表或开课列表
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/subjectList", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getSubjectList(@RequestParam Integer id) {
        UserEntity entity = userService.selectOne(id);
        List<CourseDTO> list;
        if (UserType.TEACHER.equals(entity.getType())) {
            list = service.selectCourseDTOListTch(entity.getUserId());
        } else {
            list = service.selectCourseDTOListStu(entity.getUserId());
        }
        return new RestResult.Builder(200).data(list).build();
    }
}
