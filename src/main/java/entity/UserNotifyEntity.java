package entity;

public class UserNotifyEntity {

    private Integer userNotifyId;

    private Integer notifyId;

    private Integer userId;

    private Boolean isRead = false;

    public Integer getUserNotifyId() {
        return userNotifyId;
    }

    public void setUserNotifyId(Integer userNotifyId) {
        this.userNotifyId = userNotifyId;
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

    public Boolean getRead() {
        return isRead;
    }

    public void setRead(Boolean read) {
        isRead = read;
    }

    @Override
    public String toString() {
        return "UserNotifyEntity{" +
                "userNotifyId=" + userNotifyId +
                ", notifyId=" + notifyId +
                ", userId=" + userId +
                ", isRead=" + isRead +
                '}';
    }

    public UserNotifyEntity(Integer notifyId, Integer userId) {
        this.notifyId = notifyId;
        this.userId = userId;
    }

    public UserNotifyEntity() {
    }
}