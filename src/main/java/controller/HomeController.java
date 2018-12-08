package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserServiceImpl;

@Controller
public class HomeController {

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    private final UserServiceImpl service;

    @Autowired
    public HomeController(UserServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/home")
    public String home() {
        return "home";
    }

    @RequestMapping("/main")
    public String main() {
        return "main";
    }

}
