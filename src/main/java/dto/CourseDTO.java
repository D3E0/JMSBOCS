package dto;

/**
 * @author yan
 * @date 2018/11/24 13:24
 * @descripition
 */
public class CourseDTO {
    private Integer courseId;

    private String courseName;

    private Integer teacherId;

    private String teacherName;

    private String academicYear;

    private String courseDescription;

    private String semester;

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester;
    }

    @Override
    public String toString() {
        return "CourseDTO{" +
                "courseId=" + courseId +
                ", courseName='" + courseName + '\'' +
                ", teacherName=" + teacherName +
                ", academicYear='" + academicYear + '\'' +
                ", courseDescription='" + courseDescription + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }
}
