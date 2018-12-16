package dto;

import java.io.Serializable;

/**
 * @author yan
 * @date 2018/11/24 12:45
 * @descripition
 */
public class JobItemDTO implements Serializable {
    private Integer jobId;

    private String jobTitle;

    private String jobContent;

    private String jobBeginTime;

    private String jobEndTime;

    private String courseName;
    private boolean isStarted;
    private int courseId;
    private int time;
    private boolean isEnded;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public Integer getJobId() {
        return jobId;
    }

    public void setJobId(Integer jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getJobContent() {
        return jobContent;
    }

    public void setJobContent(String jobContent) {
        this.jobContent = jobContent;
    }

    public String getJobBeginTime() {
        return jobBeginTime;
    }

    public void setJobBeginTime(String jobBeginTime) {
        this.jobBeginTime = jobBeginTime;
    }

    public String getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(String jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public boolean isStarted() {
        return isStarted;
    }

    public void setStarted(boolean started) {
        isStarted = started;
    }

    public boolean isEnded() {
        return isEnded;
    }

    public void setEnded(boolean ended) {
        isEnded = ended;
    }

    @Override
    public String toString() {
        return "JobItemDTO{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobContent='" + jobContent + '\'' +
                ", jobBeginTime='" + jobBeginTime + '\'' +
                ", jobEndTime='" + jobEndTime + '\'' +
                ", courseName='" + courseName + '\'' +
                ", isStarted=" + isStarted +
                ", courseId=" + courseId +
                ", time=" + time +
                ", isEnded=" + isEnded +
                '}';
    }
}
