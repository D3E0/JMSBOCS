package entity;

import java.util.Date;

public class AnnouncementEntity {

    private Integer announcementId;

    private Integer courseId;

    private Date announcementTime;

    private String announcementTitle;

    private String announcementContent;

    public Integer getAnnouncementId() {
        return announcementId;
    }

    public void setAnnouncementId(Integer announcementId) {
        this.announcementId = announcementId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Date getAnnouncementTime() {
        return announcementTime;
    }

    public void setAnnouncementTime(Date announcementTime) {
        this.announcementTime = announcementTime;
    }

    public String getAnnouncementTitle() {
        return announcementTitle;
    }

    public void setAnnouncementTitle(String announcementTitle) {
        this.announcementTitle = announcementTitle == null ? null : announcementTitle.trim();
    }

    public String getAnnouncementContent() {
        return announcementContent;
    }

    public void setAnnouncementContent(String announcementContent) {
        this.announcementContent = announcementContent == null ? null : announcementContent.trim();
    }

    public AnnouncementEntity() {
    }



    @Override
    public String toString() {
        return "AnnouncementEntity{" +
                "announcementId=" + announcementId +
                ", courseId=" + courseId +
                ", announcementTitle='" + announcementTitle + '\'' +
                '}';
    }
}