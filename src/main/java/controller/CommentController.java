package controller;

import dto.RootCommentDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.CommentService;
import service.CommentServiceImpl;
import util.RestResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
public class CommentController {

    private static final Logger logger = LogManager.getLogger(CommentController.class);

    private final CommentService service;

    @Autowired
    public CommentController(CommentServiceImpl service) {
        this.service = service;
    }

    /**
     * 课程的讨论
     *
     * @param courseId
     * @return
     */
    @RequestMapping(value = "/api/comment", method = RequestMethod.GET)
    @ResponseBody
    public RestResult getComments(@RequestParam Integer courseId) {
        logger.info("query comment under course ==> " + courseId);
        List<RootCommentDTO> list = service.selectRootComments(courseId);
        Long count = service.getCount(courseId);
        logger.info("count ==> " + count);
        logger.info(list);
        return new RestResult.Builder(200).count(count).data(list).build();
    }

    /**
     * 添加评论
     *
     * @param commentContent
     * @param userId
     * @param courseId
     * @param rootCommentId
     * @param replyCommentId
     * @return
     */
    @RequestMapping(value = "/api/comment", method = RequestMethod.POST)
    @ResponseBody
    public RestResult saveComment(@RequestParam() String commentContent,
                                    @RequestParam() Integer userId,
                                    @RequestParam(required = false) Integer courseId,
                                    @RequestParam(required = false) Integer rootCommentId,
                                    @RequestParam(required = false) Integer replyCommentId,
                                    HttpServletRequest request) {
        logger.info(String.format("save comment {%d (%d) (%d, %d) %s}", userId, courseId, replyCommentId, rootCommentId, commentContent));
        String userAgent = request.getHeader("User-Agent");
        int res = 0;
        if (courseId != null) {
            res = service.saveRootComments(commentContent, userId, userAgent, courseId);
        } else if (rootCommentId != null && replyCommentId != null) {
            res = service.saveReplyComments(commentContent, userId, userAgent, rootCommentId, replyCommentId);
        }
        logger.info("res ===> " + res);
        if (res > 0) {
            return new RestResult.Builder(200).message("success").build();
        } else {
            return new RestResult.Builder(500).message("fail").build();
        }
    }
}
