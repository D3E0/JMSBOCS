package dto;

import java.util.Date;
import java.util.List;

/**
 * 数据传输对象
 */
public class RootCommentDTO {
    private Integer commentId;
    private Integer courseId;
    private Date commentTime;
    private String commentContent;
    private Integer userId;
    private String username;
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

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getCommentTime() {
        return commentTime;
    }

    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
