package controller;

import entity.UserEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import service.UserService;
import util.RestResult;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    private static final Logger logger = LogManager.getLogger(UserController.class);

    private final UserService service;

    @Autowired
    public UserController(UserService service) {
        this.service = service;
    }

    @RequestMapping("/user")
    @ResponseBody
    public RestResult getUser() {
        logger.info(service.getUserList());
        UserEntity entity = new UserEntity();
        entity.setUsername("entity");
        entity.setPassword("password");

        UserEntity entity1 = new UserEntity();
        entity1.setPassword("username");
        entity1.setPassword("entity1");

        UserEntity entity2 = new UserEntity();
        entity2.setUsername("entity2");
        entity2.setPassword("table");
        List<UserEntity> list = new ArrayList<UserEntity>();
        list.add(entity);
        list.add(entity1);
        list.add(entity2);
        return new RestResult.Builder(0).data(list).count(list.size()).build();
    }
}
