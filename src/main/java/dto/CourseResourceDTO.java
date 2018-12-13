package dto;

public class CourseResourceDTO {
    private Integer courseResourceId;
    private Integer courseId;
    private String filename;
    private String courseName;
    private String academicYear;
    private String semester;
    private static final String PREFIX = "resource";

    public String getFullPath() {
        return String.format("%s-%s-%s-%s-%s", PREFIX, courseName, academicYear, semester, filename);
    }

    public CourseResourceDTO() {
    }

    public Integer getCourseId() {
        return courseId;
    }

    @Override
    public String toString() {
        return "CourseResourceDTO{" +
                "courseResourceId=" + courseResourceId +
                ", courseId=" + courseId +
                ", filename='" + filename + '\'' +
                ", courseName='" + courseName + '\'' +
                ", academicYear='" + academicYear + '\'' +
                ", semester='" + semester + '\'' +
                '}';
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Integer getCourseResourceId() {
        return courseResourceId;
    }

    public void setCourseResourceId(Integer courseResourceId) {
        this.courseResourceId = courseResourceId;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
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
}
