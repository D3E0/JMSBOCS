package vo;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.Objects;

/**
 * @author yan
 * @date 2018/12/6 12:15
 * @descripition
 */
public class JobVO {
    private Integer jobId;

    private String jobTitle;

    private String jobContent;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jobBeginTime;
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date jobEndTime;

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

    @Override
    public String toString() {
        return "JobVO{" +
                "jobId=" + jobId +
                ", jobTitle='" + jobTitle + '\'' +
                ", jobContent='" + jobContent + '\'' +
                ", jobBeginTime=" + jobBeginTime +
                ", jobEndTime=" + jobEndTime +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof JobVO)) return false;
        JobVO jobVO = (JobVO) o;
        return getJobId().equals(jobVO.getJobId()) &&
                getJobTitle().equals(jobVO.getJobTitle()) &&
                getJobContent().equals(jobVO.getJobContent()) &&
                getJobBeginTime().equals(jobVO.getJobBeginTime()) &&
                getJobEndTime().equals(jobVO.getJobEndTime());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getJobId(), getJobTitle(), getJobContent(), getJobBeginTime(), getJobEndTime());
    }
}
