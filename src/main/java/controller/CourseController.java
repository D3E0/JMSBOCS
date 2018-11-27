package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yan
 * @date 2018/11/25 14:51
 * @descripition
 */
@Controller
public class CourseController {
    @RequestMapping(value = "courselist", method = RequestMethod.GET)
    public String courseList() {
        return "courselist";
    }

    @RequestMapping(value = "course", method = RequestMethod.GET)
    public String course() {
        return "course";
    }

    @RequestMapping(value = "teacher", method = RequestMethod.GET)
    public String teacher() {
        return "teacher";
    }

    @RequestMapping(value = "filelist", method = RequestMethod.GET)
    public String filelist() {
        return "filelist";
    }
}
