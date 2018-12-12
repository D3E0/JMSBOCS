package controller;

import entity.AnnouncementEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AnnouncementService;
import util.RestResult;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class AnnouncementController {

    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);

    private final AnnouncementService service;

    @Autowired
    public AnnouncementController(AnnouncementService service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/announcement", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getAnnouncement(@RequestParam Integer id) {
        AnnouncementEntity entity = service.selectOne(id);
        logger.info(String.format("query announcement{%d} %s", id, entity));
        return new RestResult.Builder(200).data(entity).build();
    }

    @RequestMapping(value = "/api/announcementList", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getAnnouncementList(@RequestParam Integer courseId) {
        logger.info(String.format("to query announcement under course ==> %d", courseId));
        List<AnnouncementEntity> list = service.selectCourseAnnouncement(courseId);
        logger.info(String.format("announcement %d ==> %s", list.size(), list));
        return new RestResult.Builder(200).data(list).build();
    }

    @RequestMapping(value = "/api/announcement", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult deleteAnnouncement(@RequestParam Integer id) {
        int res = service.delete(id);
//        int res = 1;
        logger.info(String.format("delete announcement{%d} ==> %d", id, res));
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(200).message("fail").build();
        }
    }

    @RequestMapping(value = "/api/announcement", method = RequestMethod.POST)
    @ResponseBody
    public RestResult deleteAnnouncement(@RequestParam Integer courseId,
                                         @RequestParam String title,
                                         @RequestParam String content,
                                         HttpServletResponse response) {
        AnnouncementEntity entity = new AnnouncementEntity();
        entity.setAnnouncementContent(content);
        entity.setAnnouncementTitle(title);
        entity.setCourseId(courseId);
        entity.setAnnouncementTime(new Date());
        int res = service.save(entity);
        logger.info(String.format("to save announcement ==> %s", entity));
        logger.info("save announcement %d" + res);
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Origin", "*");
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(200).message("fail").build();
        }
    }

}
