package vo;

import dto.CourseDTO;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/8 20:44
 * @descripition
 */
public class CourseVO {
    private int count;
    private List<CourseDTO> courseList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<CourseDTO> getCourseList() {
        return courseList;
    }

    public void setCourseList(List<CourseDTO> courseList) {
        this.courseList = courseList;
    }

    @Override
    public String toString() {
        return "CourseVO{" +
                "count=" + count +
                ", courseList=" + courseList +
                '}';
    }
}
