package controller;

import dto.NotifyDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.NotifyService;
import util.RestResult;

import java.util.List;

@Controller
public class NotifyController {

    private static final Logger logger = LogManager.getLogger(NotifyController.class);
    private final NotifyService notifyService;

    @Autowired
    public NotifyController(NotifyService notifyService) {
        this.notifyService = notifyService;
    }

    @RequestMapping(value = "/notify", method = RequestMethod.GET)
    public String getNotifypage() {
        return "notify";
    }

    @RequestMapping(value = "/api/notify", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getUserNotifyList(@RequestParam int userId) {
        List<NotifyDTO> list = notifyService.selectUserNotifyList(userId);
        logger.info(list);
        return new RestResult.Builder(200).data(list).build();
    }

    @RequestMapping(value = "/api/notify", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult deleteUserNotify(@RequestParam int id) {
        int res = notifyService.deleteUserNotify(id);
        logger.info(String.format("delete UserNotify{%d} res %d", id, res));
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(500).message("fail").build();
        }
    }


}
