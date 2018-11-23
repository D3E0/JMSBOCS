package mapper;

import dto.CommentDTO;
import entity.CommentEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface CommentMapper {
    List<CommentEntity> getComments();

    CommentEntity getComment(int id);

    List<CommentDTO> getCommentsDetail();

    CommentDTO getCommentDetail(int cid);

    void updateCommentRoot(@Param("cid") int cid, @Param("pid") int pid);

    int saveComment(CommentEntity entity);


}
