import config.RootConfig;
import entity.StudentCourseEntity;
import mapper.StudentCourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class StudentCourseTest {

    @Autowired
    StudentCourseMapper mapper;

    @Test
    public void deleteList() {
        StudentCourseEntity entity = new StudentCourseEntity(1, 1);
        StudentCourseEntity entity1 = new StudentCourseEntity(1, 2);
        List<StudentCourseEntity> list = new ArrayList<StudentCourseEntity>();
        list.add(entity);
        list.add(entity1);
        int res = mapper.deleteList(list);
        System.out.println(res);
    }

    @Test
    public void saveList() {
        StudentCourseEntity entity = new StudentCourseEntity(1, 1);
        StudentCourseEntity entity1 = new StudentCourseEntity(1, 2);
        StudentCourseEntity entity2 = new StudentCourseEntity(2, 1);
        StudentCourseEntity entity3 = new StudentCourseEntity(2, 2);
        StudentCourseEntity entity4 = new StudentCourseEntity(3, 1);
        StudentCourseEntity entity5 = new StudentCourseEntity(3, 2);
        List<StudentCourseEntity> list = new ArrayList<StudentCourseEntity>();
//        list.add(entity);
//        list.add(entity1);
//        list.add(entity2);
//        list.add(entity3);
//        list.add(entity4);
//        list.add(entity5);
        int res = mapper.saveListIgnore(list);
        System.out.println(res);
    }
}
