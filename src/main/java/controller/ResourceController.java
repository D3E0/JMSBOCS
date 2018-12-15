package controller;

import dto.CourseResourceDTO;
import entity.CourseResourceEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FileService;
import service.ResourceService;
import util.RestResult;
import vo.FileVO;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * @author ACM-PC
 */
@Controller
public class ResourceController {
    private static final Logger logger = LogManager.getLogger(ResourceController.class);

    private final ResourceService service;
    private final FileService fileService;

    @Autowired
    public ResourceController(ResourceService service, FileService fileService) {
        this.service = service;
        this.fileService = fileService;
    }

    /**
     * 获取课程资源对应链接
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/resource", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getResource(@RequestParam Integer id) {
        CourseResourceDTO dto = service.getCourseResourceDTO(id);
        logger.info(String.format("query resource %d ==> %s", id, dto));
        if (dto == null) {
            return new RestResult.Builder(200).message("fail").build();
        }
        FileVO fileVO = fileService.getResource(dto.getCourseId(), dto.getFullPath());
        logger.info(String.format("query resource vo %d ==> %s", id, fileVO));
        return new RestResult.Builder(200).message("success").data(fileVO).build();
    }

    /**
     * 获取课程资源列表
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/api/resourceList", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getResourceList(@RequestParam Integer courseId) {
        logger.info(String.format("to query resource under course ==> %d", courseId));
        List<CourseResourceEntity> list = service.selectCourseResource(courseId);
        logger.info(String.format("resource %d ==> %s", list.size(), list));
        return new RestResult.Builder(200).data(list).build();
    }

    /**
     * 删除课程资源
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/resource", method = RequestMethod.DELETE)
    @ResponseBody
    public RestResult deleteResource(@RequestParam Integer id) {
        CourseResourceDTO dto = service.getCourseResourceDTO(id);
        if (dto != null) {
            int res = service.delete(id);
            logger.info(String.format("delete resource %s ==> %d", dto, res));
            if (res > 0) {
                fileService.deleteResource(dto.getCourseId(), dto.getFullPath());
                return new RestResult.Builder(200).message("success").build();
            }
        }
        return new RestResult.Builder(200).message("fail").build();
    }

    /**
     * 获取课程的资源上传 token
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/api/token", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getUploadToken(@RequestParam Integer id) {
        String token = fileService.getUploadToken(id);
        logger.info(String.format("query course upload token %d ==> %s", id, token));
        return new RestResult.Builder(200).data(token).build();
    }

    /**
     * 保存资源信息到数据库
     *
     * @param courseId
     * @param filename
     * @return
     */
    @RequestMapping(value = "/api/resource", method = RequestMethod.POST)
    @ResponseBody
    public RestResult saveResource(@RequestParam Integer courseId,
                                   @RequestParam String filename) {
        CourseResourceEntity entity = new CourseResourceEntity(courseId, filename, new Date());
        int res = service.save(entity);
        logger.info(String.format("save resource %s ==> %d", entity, res));
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(200).message("fail").build();
        }
    }


}
