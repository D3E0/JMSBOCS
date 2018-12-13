package vo;

import java.util.Date;

/**
 * @author yan
 * @date 2018/12/12 13:01
 * @descripition
 */
public class AddJobVO {
    private String jobTitle;
    private String jobContent;
    private Date jobBeginTime;
    private Date jobEndTime;
    private int courseId;

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

    public Date getJobBeginTime() {
        return jobBeginTime;
    }

    public void setJobBeginTime(Date jobBeginTime) {
        this.jobBeginTime = jobBeginTime;
    }

    public Date getJobEndTime() {
        return jobEndTime;
    }

    public void setJobEndTime(Date jobEndTime) {
        this.jobEndTime = jobEndTime;
    }

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }
}
