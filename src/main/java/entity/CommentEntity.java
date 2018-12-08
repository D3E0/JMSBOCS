package entity;

import java.util.Date;

public class CommentEntity {

    private Integer commentId;

    private Integer courseId;

    private Date commentTime;

    private String commentContent;

    private Integer rootCommentId;

    private Integer replyCommentId;

    private Integer userId;

    private String userAgent;

    public String getUserAgent() {
        return userAgent;
    }

    public void setUserAgent(String userAgent) {
        this.userAgent = userAgent;
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
        this.commentContent = commentContent == null ? null : commentContent.trim();
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

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public CommentEntity() {
    }

    @Override
    public String toString() {
        return "CommentEntity{" +
                "commentId=" + commentId +
                ", courseId=" + courseId +
                ", commentTime=" + commentTime +
                ", commentContent='" + commentContent + '\'' +
                ", rootCommentId=" + rootCommentId +
                ", replyCommentId=" + replyCommentId +
                ", userId=" + userId +
                '}';
    }

    public CommentEntity(Integer courseId, String commentContent, Integer userId) {
        this.courseId = courseId;
        this.commentContent = commentContent;
        this.userId = userId;
    }

    public CommentEntity(Date commentTime, String commentContent, Integer userId, String userAgent) {
        this.commentTime = commentTime;
        this.userAgent = userAgent;
        this.commentContent = commentContent;
        this.userId = userId;
    }


}