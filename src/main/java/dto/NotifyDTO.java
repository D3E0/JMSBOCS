package dto;

import entity.NotifyType;

import java.util.Date;

/**
 * @author ACM-PC
 */
public class NotifyDTO {
    private Integer id;
    private Integer notifyId;
    private Integer userId;
    private NotifyType type;
    private Date time;
    private Boolean isRead;
    private String title;
    private String content;

    @Override
    public String toString() {
        return "NotifyDTO{" +
                "id=" + id +
                ", notifyId=" + notifyId +
                ", userId=" + userId +
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
