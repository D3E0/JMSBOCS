package vo;

import dto.UserSDTO;

import java.io.Serializable;
import java.util.Set;

/**
 * @author yan
 * @date 2018/12/8 20:44
 * @descripition
 */
public class CourseVO implements Serializable {
    private Integer courseId;
    private Set<UserSDTO> studentList;

    @Override
    public String toString() {
        return "CourseVO{" +
                "courseId=" + courseId +
                ", studentList=" + studentList +
                '}';
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public Set<UserSDTO> getStudentList() {
        return studentList;
    }

    public void setStudentList(Set<UserSDTO> studentList) {
        this.studentList = studentList;
    }
}
