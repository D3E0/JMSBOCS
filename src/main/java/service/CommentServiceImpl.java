package service;

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

    public List<RootCommentDTO> getCommentsDetail() {
        return mapper.getCommentsDetail();
    }

    public RootCommentDTO getCommentDetail(int cid) {
        return mapper.getCommentDetail(cid);
    }

    public CommentEntity getComment(int id) {
        return mapper.getComment(id);
    }

    /**
     * TODO 事务
     */
    public int saveComment(CommentEntity entity) {
        int cnt = mapper.saveComment(entity);
        if (cnt < 0) {
            return 0;
        } else {
            int id = entity.getCommentId();
            if (entity.getRootCommentId() == null) {
                updateCommentRoot(id, id);
            }
            return id;
        }
    }

    public void updateCommentRoot(int cid, int pid) {
        mapper.updateCommentRoot(cid, pid);
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
    public int saveRootComments(String content, Integer userId, Integer courseId) {
        CommentEntity entity = new CommentEntity(new Date(), content, userId);
        entity.setCourseId(courseId);
        return mapper.save(entity);
    }

    @Override
    public int saveReplyComments(String content, Integer userId, Integer rootId, Integer replyId) {
        CommentEntity entity = new CommentEntity(new Date(), content, userId);
        entity.setReplyCommentId(replyId);
        entity.setReplyCommentId(rootId);
        return mapper.save(entity);
    }


}
