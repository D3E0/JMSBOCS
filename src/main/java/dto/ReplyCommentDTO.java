package dto;

import java.util.Date;

/**
 * 数据传输对象
 */
public class ReplyCommentDTO {
    private Integer commentId;
    private Date commentTime;
    private String commentContent;
    private Integer userId;
    private String username;
    private Integer rootCommentId;
    private Integer replyCommentId;
    private Integer replyUserId;
    private String replyUsername;

    @Override
    public String toString() {
        return "ReplyCommentDTO{" +
                "commentId=" + commentId +
                ", commentTime=" + commentTime +
                ", userId=" + userId +
                ", username='" + username + '\'' +
                ", rootCommentId=" + rootCommentId +
                ", replyCommentId=" + replyCommentId +
                ", replyUserId=" + replyUserId +
                ", replyUsername='" + replyUsername + '\'' +
                '}';
    }

    public Integer getCommentId() {
        return commentId;
    }

    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
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

    public Integer getRootCommentId() {
        return rootCommentId;
    }

    public void setRootCommentId(Integer rootCommentId) {
        this.rootCommentId = rootCommentId;
    }

    public Integer getReplyCommentId() {
        return replyCommentId;
    }

    public void setReplyCommentId(Integer replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public String getReplyUsername() {
        return replyUsername;
    }

    public void setReplyUsername(String replyUsername) {
        this.replyUsername = replyUsername;
    }
}
