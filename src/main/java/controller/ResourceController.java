package controller;

import entity.AnnouncementEntity;
import entity.CourseResourceEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.ResourceService;
import util.RestResult;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
public class ResourceController {
    private static final Logger logger = LogManager.getLogger(ResourceController.class);

    private final ResourceService service;

    @Autowired
    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @RequestMapping(value = "/api/resource", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getResource(@RequestParam Integer id) {
        CourseResourceEntity entity = service.selectOne(id);
        logger.info(String.format("query resource{%d} %s", id, entity));
        return new RestResult.Builder(200).data(entity).build();
    }

    @RequestMapping(value = "/api/resourceList", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getResourceList(@RequestParam Integer courseId) {
        logger.info(String.format("to query resource under course ==> %d", courseId));
        List<CourseResourceEntity> list = service.selectCourseResource(courseId);
        logger.info(String.format("resource %d ==> %s", list.size(), list));
        return new RestResult.Builder(200).data(list).build();
    }

    @RequestMapping(value = "/api/resource", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult deleteAnnouncement(@RequestParam Integer id) {
        int res = service.delete(id);
        logger.info(String.format("delete resource{%d} ==> %d", id, res));
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(200).message("fail").build();
        }
    }

    @RequestMapping(value = "/api/resource", method = RequestMethod.POST)
    @ResponseBody
    public RestResult saveAnnouncement(@RequestParam Integer courseId,
                                       @RequestParam String title,
                                       @RequestParam String content,
                                       HttpServletResponse response) {
        int res = 0;
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
