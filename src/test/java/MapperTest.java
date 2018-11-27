import config.RootConfig;
import dto.CourseItemDto;
import dto.JobItemDTO;
import mapper.CourseMapper;
import mapper.JobMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 11:57
 * @descripition
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class MapperTest {
    private JobMapper jobMapper;
    private CourseMapper courseMapper;
    @Autowired
    public void setMapper(CourseMapper mapper) {
        this.courseMapper = mapper;
    }

    @Autowired
    public void setJobMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Test
    public void testJob() {
        List<JobItemDTO> list=jobMapper.findJobListByIdAndKeyword(1,0,"");
    }
    @Test
    public void testCourse() {
        List<CourseItemDto> list=courseMapper.findCourseListById(1,1);
        System.out.println(list.get(0).toString());
    }
}
