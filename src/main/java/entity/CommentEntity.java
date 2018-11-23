package entity;

import java.util.Date;

public class CommentEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_id
     *
     * @mbg.generated
     */
    private Integer commentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.course_id
     *
     * @mbg.generated
     */
    private Integer courseId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_time
     *
     * @mbg.generated
     */
    private Date commentTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.comment_content
     *
     * @mbg.generated
     */
    private String commentContent;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.root_comment_id
     *
     * @mbg.generated
     */
    private Integer rootCommentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.reply_comment_id
     *
     * @mbg.generated
     */
    private Integer replyCommentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column comment.user_id
     *
     * @mbg.generated
     */
    private Integer userId;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_id
     *
     * @return the value of comment.comment_id
     * @mbg.generated
     */
    public Integer getCommentId() {
        return commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_id
     *
     * @param commentId the value for comment.comment_id
     * @mbg.generated
     */
    public void setCommentId(Integer commentId) {
        this.commentId = commentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.course_id
     *
     * @return the value of comment.course_id
     * @mbg.generated
     */
    public Integer getCourseId() {
        return courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.course_id
     *
     * @param courseId the value for comment.course_id
     * @mbg.generated
     */
    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_time
     *
     * @return the value of comment.comment_time
     * @mbg.generated
     */
    public Date getCommentTime() {
        return commentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_time
     *
     * @param commentTime the value for comment.comment_time
     * @mbg.generated
     */
    public void setCommentTime(Date commentTime) {
        this.commentTime = commentTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.comment_content
     *
     * @return the value of comment.comment_content
     * @mbg.generated
     */
    public String getCommentContent() {
        return commentContent;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.comment_content
     *
     * @param commentContent the value for comment.comment_content
     * @mbg.generated
     */
    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent == null ? null : commentContent.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.root_comment_id
     *
     * @return the value of comment.root_comment_id
     * @mbg.generated
     */
    public Integer getRootCommentId() {
        return rootCommentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.root_comment_id
     *
     * @param rootCommentId the value for comment.root_comment_id
     * @mbg.generated
     */
    public void setRootCommentId(Integer rootCommentId) {
        this.rootCommentId = rootCommentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.reply_comment_id
     *
     * @return the value of comment.reply_comment_id
     * @mbg.generated
     */
    public Integer getReplyCommentId() {
        return replyCommentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.reply_comment_id
     *
     * @param replyCommentId the value for comment.reply_comment_id
     * @mbg.generated
     */
    public void setReplyCommentId(Integer replyCommentId) {
        this.replyCommentId = replyCommentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column comment.user_id
     *
     * @return the value of comment.user_id
     * @mbg.generated
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column comment.user_id
     *
     * @param userId the value for comment.user_id
     * @mbg.generated
     */
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
}