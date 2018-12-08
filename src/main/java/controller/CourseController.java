package controller;

import dto.CourseItemDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CourseService;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/25 14:51
 * @descripition
 */
@Controller
public class CourseController {
    private CourseService courseService;
    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

    @RequestMapping(value = "courseList", method = RequestMethod.GET)
    public String courseList() {
        return "courseList";
    }
    @ResponseBody
    @RequestMapping(value = "courseList", method = RequestMethod.POST)
    public List<CourseItemDto> getCourseList(int studentId,int page,String keyword) {
        return courseService.findCourseListById(studentId,page,keyword);
    }

    @ResponseBody
    @RequestMapping(value = "courseCount", method = RequestMethod.POST)
    public int getCourseCount(int studentId,String keyword) {
        return courseService.countCourseById(studentId,keyword);
    }

    @RequestMapping(value = "course", method = RequestMethod.GET)
    public String course(Model model,int courseId, String courseName) {
        model.addAttribute("courseName",courseName);
        model.addAttribute("courseId",courseId);
        return "course";
    }

    @RequestMapping(value = "teacher", method = RequestMethod.GET)
    public String teacher(int courseId) {

        return "teacher";
    }

    @RequestMapping(value = "fileList", method = RequestMethod.GET)
    public String fileList() {
        return "fileList";
    }
}
