package controller;

import dto.CourseAnnouncementDTO;
import entity.AnnouncementEntity;
import entity.UserEntity;
import entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.AnnouncementService;
import service.UserService;
import util.RestResult;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class AnnouncementController {

    private static final Logger logger = LogManager.getLogger(AnnouncementController.class);

    private final AnnouncementService service;
    private final UserService userService;

    @Autowired
    public AnnouncementController(AnnouncementService service, UserService userService) {
        this.service = service;
        this.userService = userService;
    }

    @RequestMapping(value = "/api/announcement", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getAnnouncement(@RequestParam Integer id) {
        AnnouncementEntity entity = service.selectOne(id);
        logger.info(String.format("query announcement{%d} %s", id, entity));
        return new RestResult.Builder(200).data(entity).build();
    }

    @RequestMapping(value = "/api/announcements", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getAllAnnouncement(@RequestParam Integer userId) {
        logger.info(String.format("to query all announcement for user ==> %d", userId));
        UserEntity entity = userService.selectOne(userId);
        List<CourseAnnouncementDTO> list = new ArrayList<CourseAnnouncementDTO>();
        if (entity != null) {
            int id = entity.getUserId();
            if (UserType.TEACHER.equals(entity.getType())) {
                list = service.selectTchAnnouncement(id);
            } else if (UserType.STUDENT.equals(entity.getType())) {
                list = service.selectStuAnnouncement(id);
            }
        }
        logger.info(String.format("announcement %d ==> %s", list.size(), list));
        return new RestResult.Builder(200).data(list).build();
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
        logger.info(String.format("delete announcement{%d} ==> %d", id, res));
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(200).message("fail").build();
        }
    }

    @RequestMapping(value = "/api/announcement", method = RequestMethod.POST)
    @ResponseBody
    public RestResult saveAnnouncement(@RequestParam Integer courseId,
                                       @RequestParam String title,
                                       @RequestParam String content) {
        AnnouncementEntity entity = new AnnouncementEntity();
        entity.setAnnouncementContent(content);
        entity.setAnnouncementTitle(title);
        entity.setCourseId(courseId);
        entity.setAnnouncementTime(new Date());
        int res = service.save(entity);
        logger.info(String.format("to save announcement ==> %s", entity));
        logger.info("save announcement %d ==> " + res);
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(200).message("fail").build();
        }
    }

}
