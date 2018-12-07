package controller;

import dto.NotifyDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.NotifyService;
import service.NotifyServiceImpl;
import util.RestResult;

import java.util.List;

@Controller
public class NotifyController {

    private static final Logger logger = LogManager.getLogger(NotifyController.class);
    private final NotifyService notifyServiceImpl;

    @Autowired
    public NotifyController(NotifyServiceImpl notifyServiceImpl) {
        this.notifyServiceImpl = notifyServiceImpl;
    }

    /**
     * 返回用户的消息列表与消息总数
     *
     * @param userId
     * @return
     */
    @RequestMapping(value = "/api/notify", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getUserNotifyList(@RequestParam Integer userId,
                                        @RequestParam(defaultValue = "1") Integer page,
                                        @RequestParam(defaultValue = "10") Integer limit) {
        List<NotifyDTO> list = notifyServiceImpl.selectUserNotifyList(userId, page, limit);
        Long count = notifyServiceImpl.getCount(userId);
        logger.info(String.format("user{%d} query notify count ==> %d", userId, count));
        logger.info(list);
        return new RestResult.Builder(200).count(count).data(list).build();
    }

    /**
     * 用户删除消息
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/notify", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult deleteUserNotify(@RequestBody List<Integer> id) {
        int res = 0;
        for (Integer integer : id) {
            res += notifyServiceImpl.deleteUserNotify(integer);
        }
        logger.info(String.format("delete UserNotify{%s} res ==> %d", id, res));
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(500).message("fail").build();
        }
    }


}
