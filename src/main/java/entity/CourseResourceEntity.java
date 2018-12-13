package entity;

import java.util.Date;

public class CourseResourceEntity {
    private Integer courseResourceId;

    private String courseResourcePath;

    private Integer courseId;

    private String courseResourceFilename;

    private Date uploadTime;

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public Integer getCourseResourceId() {
        return courseResourceId;
    }

    public void setCourseResourceId(Integer courseResourceId) {
        this.courseResourceId = courseResourceId;
    }

    public String getCourseResourcePath() {
        return courseResourcePath;
    }

    public void setCourseResourcePath(String courseResourcePath) {
        this.courseResourcePath = courseResourcePath == null ? null : courseResourcePath.trim();
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseResourceFilename() {
        return courseResourceFilename;
    }

    public void setCourseResourceFilename(String courseResourceFilename) {
        this.courseResourceFilename = courseResourceFilename == null ? null : courseResourceFilename.trim();
    }
}