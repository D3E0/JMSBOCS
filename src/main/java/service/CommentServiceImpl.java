package service;

import dto.CommentDTO;
import dto.ReplyCommentDTO;
import dto.RootCommentDTO;
import entity.CommentEntity;
import mapper.CommentMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @author ACM-PC
 */
@Service
public class CommentServiceImpl implements CommentService {
    private static final Logger logger = LogManager.getLogger(CommentServiceImpl.class);
    private final CommentMapper mapper;

    @Autowired
    public CommentServiceImpl(CommentMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public List<ReplyCommentDTO> selectReplyComments(int rootCommentId) {
        return mapper.selectReplyComments(rootCommentId);
    }

    @Override
    public List<RootCommentDTO> selectRootComments(int courseId) {
        return mapper.selectRootComments(courseId);
    }

    @Override
    public int saveRootComments(String content, Integer userId, String userAgent, Integer courseId) {
        CommentEntity entity = new CommentEntity(new Date(), content, userId, userAgent);
        entity.setCourseId(courseId);
        return mapper.save(entity);
    }

    @Override
    public int saveReplyComments(String content, Integer userId, String userAgent, Integer rootId, Integer replyId) {
        CommentEntity entity = new CommentEntity(new Date(), content, userId, userAgent);
        entity.setReplyCommentId(replyId);
        entity.setRootCommentId(rootId);
        return mapper.save(entity);
    }

    @Override
    public Long getCount(int courseId) {
        return mapper.getCount(courseId);
    }


}
