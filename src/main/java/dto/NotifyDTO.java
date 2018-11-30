package dto;

import entity.NotifyType;

import java.util.Date;

public class NotifyDTO {
    private Integer id;
    private Integer notifyId;
    private Integer userId;
    private NotifyType type;
    private Date time;
    private Boolean isRead;
    private String content;
    private String title;


    @Override
    public String toString() {
        return "NotifyDTO{" +
                "id=" + id +
                ", notifyId=" + notifyId +
                ", userId=" + userId +
                ", type=" + type +
                ", time=" + time +
                ", isRead=" + isRead +
                ", content='" + content + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public NotifyType getType() {
        return type;
    }

    public void setType(NotifyType type) {
        this.type = type;
        updateTitle(type);
    }

    private void updateTitle(NotifyType type) {
        String tmp = "";
        switch (type) {
            case COMMENTREPLY:
                tmp = "评论回复提醒";
                break;
            case COMMENTUPVOTE:
                tmp = "评论点赞提醒";
                break;
            case COURSEADD:
                tmp = "课程注册提醒";
                break;
            case JOBPUBLISH:
                tmp = "作业发布提醒";
                break;
            case JOBSUBMMIT:
                tmp = "作业提交提醒";
                break;
            default:
        }
        setTitle(tmp);
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
