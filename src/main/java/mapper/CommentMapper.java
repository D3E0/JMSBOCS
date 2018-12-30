package mapper;

import dto.ReplyCommentDTO;
import dto.RootCommentDTO;
import entity.CommentEntity;
import org.springframework.stereotype.Repository;
import service.BaseService;

import java.util.List;

@Repository
public interface CommentMapper extends BaseService<CommentEntity> {

    /**
     * 根据课程查询讨论列表
     *
     * @param courseId
     * @return
     */
    List<RootCommentDTO> selectRootComments(int courseId);

    /**
     * 根据一级评论 ID 查询二级评论
     *
     * @param rootCommentId
     * @return
     */
    List<ReplyCommentDTO> selectReplyComments(int rootCommentId);

    /**
     * 返回课程评论总数
     *
     * @param courseId
     * @return
     */
    Long getCount(int courseId);

    /**
     * 查询二级评论
     *
     * @param id
     * @return
     */
    ReplyCommentDTO selectReplyComment(int id);

}
