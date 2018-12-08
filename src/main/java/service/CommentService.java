package service;

import dto.ReplyCommentDTO;
import dto.RootCommentDTO;

import java.util.List;

public interface CommentService {
    List<ReplyCommentDTO> selectReplyComments(int rootCommentId);

    List<RootCommentDTO> selectRootComments(int courseId);

    int saveRootComments(String content, Integer userId, Integer courseId);

    int saveReplyComments(String content, Integer userId, Integer rootId, Integer replyId);

}
