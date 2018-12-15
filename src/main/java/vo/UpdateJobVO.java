package vo;

/**
 * @author yan
 * @date 2018/12/6 12:15
 * @descripition
 */
public class UpdateJobVO {
    private Integer jobId;
    private String jobTitle;
    private String jobContent;
    private String jobBeginTime;
    private String jobEndTime;

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

    @Override
    public String toString() {
        return "UpdateJobVO{" +
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
        if (!(o instanceof UpdateJobVO)) return false;
        UpdateJobVO updateJobVO = (UpdateJobVO) o;
        return getJobId().equals(updateJobVO.getJobId()) &&
                getJobTitle().equals(updateJobVO.getJobTitle()) &&
                getJobContent().equals(updateJobVO.getJobContent()) &&
                getJobBeginTime().equals(updateJobVO.getJobBeginTime()) &&
                getJobEndTime().equals(updateJobVO.getJobEndTime());
    }

}
