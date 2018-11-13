package com;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import service.UserService;

public class Main {
    public static void main(String[] args) {
        String path = "spring-config.xml";
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext(path);
        UserService userService = applicationContext.getBean("userService", UserService.class);
        userService.getUser();
    }
}
