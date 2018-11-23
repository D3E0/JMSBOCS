package service;

import dto.CommentDTO;
import entity.CommentEntity;
import mapper.CommentMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private static final Logger logger = LogManager.getLogger(CommentService.class);
    private final CommentMapper commentMapper;

    @Autowired
    public CommentService(CommentMapper commentMapper) {
        this.commentMapper = commentMapper;
    }

    public List<CommentDTO> getCommentsDetail() {
        return commentMapper.getCommentsDetail();
    }

    public CommentDTO getCommentDetail(int cid) {
        return commentMapper.getCommentDetail(cid);
    }

    public CommentEntity getComment(int id) {
        return commentMapper.getComment(id);
    }

    /**
     * TODO 事务
     */
    public int saveComment(CommentEntity entity) {
        int cnt = commentMapper.saveComment(entity);
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
        commentMapper.updateCommentRoot(cid, pid);
    }
}
