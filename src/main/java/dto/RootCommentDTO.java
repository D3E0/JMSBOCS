package dto;

import java.util.List;

/**
 * 数据传输对象 二级评论
 */
public class RootCommentDTO extends CommentDTO {
    private Integer courseId;
    private List<ReplyCommentDTO> replyComments;

    @Override
    public String toString() {
        return "RootCommentDTO{" +
                "commentId=" + commentId +
                ", courseId=" + courseId +
                ", commentTime=" + commentTime +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                '}';
    }

    public List<ReplyCommentDTO> getReplyComments() {
        return replyComments;
    }

    public void setReplyComments(List<ReplyCommentDTO> replyComments) {
        this.replyComments = replyComments;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

}
