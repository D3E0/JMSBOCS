package mapper;

import dto.CommentDTO;
import dto.ReplyCommentDTO;
import dto.RootCommentDTO;
import entity.CommentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import service.BaseService;

import java.util.List;

@Component
public interface CommentMapper extends BaseService<CommentEntity> {
    List<CommentEntity> getComments();

    CommentEntity getComment(int id);

    List<CommentDTO> getCommentsDetail();

    CommentDTO getCommentDetail(int cid);

    void updateCommentRoot(@Param("cid") int cid, @Param("pid") int pid);

    int saveComment(CommentEntity entity);

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

}
