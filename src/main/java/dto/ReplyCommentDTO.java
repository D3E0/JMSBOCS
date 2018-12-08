package dto;

/**
 * 数据传输对象
 */
public class ReplyCommentDTO extends CommentDTO {
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
