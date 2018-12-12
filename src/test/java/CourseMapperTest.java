import config.RootConfig;
import entity.CourseEntity;
import mapper.CourseMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class CourseMapperTest {

    @Autowired
    private CourseMapper mapper;

    @Test
    public void testSave() {
        CourseEntity entity = new CourseEntity();
        entity.setCourseName("sdad");
        entity.setCourseId(5);
        entity.setTeacherId(1);
        int res = mapper.save(entity);
        System.out.println(res);
        System.out.println(entity.getCourseId());
    }

}
