package service;

import dto.ReplyCommentDTO;
import dto.RootCommentDTO;
import entity.CommentEntity;
import entity.NotifyEntity;
import entity.NotifyType;
import manager.NotifyManager;
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
    private final NotifyManager manager;

    @Autowired
    public CommentServiceImpl(CommentMapper mapper, NotifyManager manager) {
        this.mapper = mapper;
        this.manager = manager;
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
        int res = mapper.save(entity);
        logger.info(String.format("save root %s ==> %s", entity, res));
        return res;
    }

    @Override
    public int saveReplyComments(String content, Integer userId, String userAgent, Integer rootId, Integer replyId) {
        CommentEntity entity = new CommentEntity(new Date(), content, userId, userAgent);
        entity.setReplyCommentId(replyId);
        entity.setRootCommentId(rootId);
        int res = mapper.save(entity);
        logger.info(String.format("save reply %s ==> %s", entity, res));
        if (res > 0) {
            NotifyEntity notify = notifyTemplate(entity.getCommentId());
            boolean result = manager.add(notify);
            logger.info(String.format("save notify %s ==> %s", notify, result));
        }
        return res;
    }

    @Override
    public Long getCount(int courseId) {
        return mapper.getCount(courseId);
    }

    private ReplyCommentDTO selectReplyComment(int id) {
        return mapper.selectReplyComment(id);
    }

    private NotifyEntity notifyTemplate(Integer id) {
        ReplyCommentDTO dto = selectReplyComment(id);
        String content = String.format("%s 回复了你的评论，快去查看吧", dto.getUsername());
        String title = "评论回复提醒";
        NotifyEntity entity = new NotifyEntity(content, title, NotifyType.PEERTOPEER, new Date(), dto.getReplyUserId());
        entity.setNotifySender(dto.getUserId());
        return entity;
    }

}
