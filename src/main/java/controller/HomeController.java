package controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import service.UserService;

@Controller
public class HomeController {

    private static final Logger logger = LogManager.getLogger(HomeController.class);

    private final UserService service;

    @Autowired
    public HomeController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/home")
    public String home() {
        service.getUser();
        return "home";
    }
    @RequestMapping("/main")
    public String main() {
        service.getUser();
        return "main";
    }
}
