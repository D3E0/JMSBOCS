package dto;

import java.io.Serializable;

/**
 * @author yan
 * @date 2018/12/11 16:01
 * @descripition
 */
public class JobFilePrefix implements Serializable {
    private static final long serialVersionUID = -8349490414810421264L;
    private int courseId;
    private String jobTitle;
    private String courseName;
    private String academicYear;
    private String semester;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }
    public String getFilePrefix(){
        return courseName+"-"+academicYear+"-"+semester+"/"+jobTitle;
    }
}
