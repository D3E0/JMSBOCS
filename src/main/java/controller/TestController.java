package controller;

import entity.NotifyEntity;
import entity.NotifyType;
import manager.NotifyManager;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    private final NotifyManager notifyManager;
    private static final Logger logger = LogManager.getLogger(TestController.class);

    @Autowired
    public TestController(NotifyManager notifyManager) {
        this.notifyManager = notifyManager;
    }

    @RequestMapping("/test")
    public String test() {
        NotifyEntity entity = new NotifyEntity("content", NotifyType.COMMENTREPLY, 1, 2);
        boolean res = notifyManager.add(entity);
        logger.info(res);
        return "dsad";
    }

}
