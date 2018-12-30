package entity;

import java.util.Date;

public class NotifyEntity {

    private Integer notifyId;

    private String notifyContent;

    private String notifyTitle;

    private Date notifyTime;

    private Boolean isFinished = false;

    private NotifyType notifyType;

    private Integer notifySender;

    private Integer notifyReceiver;

    public String getNotifyTitle() {
        return notifyTitle;
    }

    public void setNotifyTitle(String notifyTitle) {
        this.notifyTitle = notifyTitle;
    }

    public Integer getNotifyId() {
        return notifyId;
    }

    public void setNotifyId(Integer notifyId) {
        this.notifyId = notifyId;
    }

    public String getNotifyContent() {
        return notifyContent;
    }

    public void setNotifyContent(String notifyContent) {
        this.notifyContent = notifyContent == null ? null : notifyContent.trim();
    }

    public Date getNotifyTime() {
        return notifyTime;
    }

    public void setNotifyTime(Date notifyTime) {
        this.notifyTime = notifyTime;
    }

    public Integer getNotifySender() {
        return notifySender;
    }

    public void setNotifySender(Integer notifySender) {
        this.notifySender = notifySender;
    }

    public NotifyType getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(NotifyType notifyType) {
        this.notifyType = notifyType;
    }

    public Boolean getFinished() {
        return isFinished;
    }

    public void setFinished(Boolean finished) {
        isFinished = finished;
    }

    public Integer getNotifyReceiver() {
        return notifyReceiver;
    }

    public void setNotifyReceiver(Integer notifyReceiver) {
        this.notifyReceiver = notifyReceiver;
    }

    @Override
    public String toString() {
        return "NotifyEntity{" +
                "notifyId=" + notifyId +
                ", notifyTitle='" + notifyTitle + '\'' +
                ", notifyType=" + notifyType +
                ", notifySender=" + notifySender +
                ", notifyReceiver=" + notifyReceiver +
                '}';
    }

    public NotifyEntity() {
    }

    public NotifyEntity(String content, String title, NotifyType notifyType, Date time, Integer receiver) {
        this.notifyTitle = title;
        this.notifyContent = content;
        this.notifyType = notifyType;
        this.notifyTime = time;
        this.notifyReceiver = receiver;
    }
}