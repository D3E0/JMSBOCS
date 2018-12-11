package entity;

public class StudentCourseEntity {

    private Integer studentCourseId;

    private Integer studentId;

    private Integer courseId;

    public Integer getStudentCourseId() {
        return studentCourseId;
    }

    public void setStudentCourseId(Integer studentCourseId) {
        this.studentCourseId = studentCourseId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public StudentCourseEntity() {
    }

    public StudentCourseEntity(Integer studentId, Integer courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}