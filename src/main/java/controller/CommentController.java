package controller;

import dto.RootCommentDTO;
import entity.CommentEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import service.CommentServiceImpl;
import util.RestResult;

import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/api")
public class CommentController {

    private static final Logger logger = LogManager.getLogger(CommentController.class);

    private final CommentServiceImpl service;

    @Autowired
    public CommentController(CommentServiceImpl service) {
        this.service = service;
    }

    @RequestMapping("/comments")
    @ResponseBody
    public RestResult getComments() {
        List<RootCommentDTO> list = service.getCommentsDetail();
        return new RestResult.Builder(0).data(list).count((long) list.size()).build();
    }

    @RequestMapping("/comment/1")
    @ResponseBody
    public RestResult getComment() {
        CommentEntity entity = service.getComment(1);
        return new RestResult.Builder(0).data(entity).build();
    }

    @RequestMapping(value = "/comment/add", method = RequestMethod.POST)
    @ResponseBody
    public RestResult addCommentGet(@RequestParam() String commentContent,
                                    @RequestParam() Integer userId,
                                    @RequestParam() Integer courseId,
                                    @RequestParam(required = false) Integer rootCommentId,
                                    @RequestParam(required = false) Integer replyCommentId) {
        logger.info(commentContent + "  " + userId + " " + courseId);
        logger.info(rootCommentId + " " + replyCommentId);
        CommentEntity entity = new CommentEntity(courseId, commentContent, userId);
        entity.setReplyCommentId(replyCommentId);
        entity.setRootCommentId(rootCommentId);
        entity.setCommentTime(new Date());
        int id = service.saveComment(entity);
        if (id == 0) {
            return new RestResult.Builder(500).build();
        } else {
            return new RestResult.Builder(0).data(service.getCommentDetail(id)).build();
        }
    }
}
