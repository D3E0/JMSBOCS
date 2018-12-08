package dto;

import java.util.Date;

public class CommentDTO {
    protected Integer commentId;
    protected Date commentTime;
    protected String userAgent;
    protected String commentContent;
    protected Integer userId;
    protected String username;

    public Integer getCommentId() {
        return commentId;
    }

    public String getUserAgent() {
        return this.userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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

}
