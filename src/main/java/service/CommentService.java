package service;

import dto.ReplyCommentDTO;
import dto.RootCommentDTO;

import java.util.List;

public interface CommentService {
    List<ReplyCommentDTO> selectReplyComments(int rootCommentId);

    List<RootCommentDTO> selectRootComments(int courseId);

    int saveRootComments(String content, Integer userId, String userAgent, Integer courseId);

    int saveReplyComments(String content, Integer userId, String userAgent, Integer rootId, Integer replyId);

    Long getCount(int courseId);

}
