package entity;

public class CourseEntity {
    private Integer courseId;

    private String courseName;

    private Integer teacherId;

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
        this.courseName = courseName == null ? null : courseName.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getAcademicYear() {
        return academicYear;
    }

    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear == null ? null : academicYear.trim();
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription == null ? null : courseDescription.trim();
    }

    public String getSemester() {
        return semester;
    }

    public void setSemester(String semester) {
        this.semester = semester == null ? null : semester.trim();
    }

    public CourseEntity() {
    }

    public CourseEntity(String courseName, Integer teacherId, String academicYear, String courseDescription, String semester) {
        this.courseName = courseName;
        this.teacherId = teacherId;
        this.academicYear = academicYear;
        this.courseDescription = courseDescription;
        this.semester = semester;
    }
}