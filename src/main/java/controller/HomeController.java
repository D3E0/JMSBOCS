package controller;

import com.alibaba.fastjson.JSONObject;
import dto.CourseDTO;
import dto.UserInfo;
import entity.UserEntity;
import entity.UserType;
import interceptor.CookieInterceptor;
import manager.AuthRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import service.CourseService;
import service.UserService;
import util.RestResult;
import util.UserSecurity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;

/**
 * @author ACM-PC
 */
@Controller
public class HomeController {

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    private final UserService service;
    private final AuthRepository repository;
    private final CourseService courseService;

    @Autowired
    public HomeController(UserService service, AuthRepository repository, CourseService courseService) {
        this.service = service;
        this.repository = repository;
        this.courseService = courseService;
    }

    /**
     * 返回登陆界面
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String doLogin() {
        return "login";
    }

    /**
     * 主页
     *
     * @return
     */
    @RequestMapping("/home")
    public String home(Model model) {
        addUserInfo(model);
        return "home";
    }

    /**
     * 个人资料界面
     *
     * @return
     */
    @RequestMapping("/user")
    public String getUserProfilePage(Model model) {
        addUserInfo(model);
        return "user";
    }

    /**
     * 课程列表界面
     *
     * @return
     */
    @RequestMapping("/subject")
    public String subject(Model model) {
        addUserInfo(model);
        return "subject";
    }

    /**
     * 课程详情界面
     *
     * @param courseId
     * @return
     */
    @RequestMapping("/course/{courseId}")
    public String course(@PathVariable Integer courseId, Model model) {
        logger.info(courseId);
        CourseDTO courseDTO = courseService.selectCourseDTO(courseId);
        if (courseDTO == null) {
            return "error";
        }
        addUserInfo(model);
        model.addAttribute("courseId", courseId);
        return "course";
    }

    /**
     * 用户登出
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping(value = "/logout")
    public String logout(HttpSession session, HttpServletRequest request,
                         HttpServletResponse response) {
        Integer id = UserSecurity.getId();
        logger.info(String.format("user %d logout", id));
        repository.deleteAuth(id);
        Enumeration<String> attributeNames = session.getAttributeNames();
        while (attributeNames.hasMoreElements()) {
            session.removeAttribute(attributeNames.nextElement());
        }
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if (CookieInterceptor.JMSBOCS_COOKIE.equals(cookie.getName())) {
                String path = "".equals(request.getContextPath()) ? "/" : request.getContextPath();
                cookie.setPath(path);
                cookie.setMaxAge(0);
                response.addCookie(cookie);
            }
        }
        return "redirect:/login";
    }

    /**
     * 处理用户登陆
     *
     * @param id
     * @param pass
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public RestResult processLogin(@RequestParam Integer id,
                                   @RequestParam String pass,
                                   HttpServletRequest request,
                                   HttpServletResponse response,
                                   Model model) {
        UserEntity entity = service.processLogin(id, pass);
        logger.info(String.format("user %d %s login ==> %s", id, pass, entity));
        if (entity == null) {
            return new RestResult.Builder(200).message("fail").build();
        } else {
            addAuthCookie(entity, response, request);
            addUserInfo(model);
            return new RestResult.Builder(200).message("success").build();
        }
    }

    private void addAuthCookie(UserEntity entity,
                               HttpServletResponse response,
                               HttpServletRequest request) {
        String auth = repository.addAuth(entity.getUserId());
        UserSecurity.setUser(entity.getUserId(), entity.getUsername(), entity.getType());
        Cookie cookie = new Cookie(CookieInterceptor.JMSBOCS_COOKIE, auth);
        String path = "".equals(request.getContextPath()) ? "/" : request.getContextPath();
        cookie.setPath(path);
        response.addCookie(cookie);
        cookie.setMaxAge(60 * 60 * 24 * 7);
        response.addCookie(cookie);
        logger.info(String.format("add cookie %s to path %s", auth, path));
    }

    private void addUserInfo(Model model) {
        UserInfo userInfo = UserSecurity.getUser();
        if (userInfo != null) {
            JSONObject object = new JSONObject();
            object.put("uid", userInfo.getId());
            object.put("name", userInfo.getName());
            object.put("isTch", userInfo.getType().equals(UserType.TEACHER));
            model.addAttribute("user", object);
        }
    }

    @RequestMapping(value = "/error")
    public String error() {
        return "error";
    }

    @RequestMapping(value = "/error/api")
    @ResponseBody
    public RestResult doError() {
        return new RestResult.Builder(500).message("fail").build();
    }

}
