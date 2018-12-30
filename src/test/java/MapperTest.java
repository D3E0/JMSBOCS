import config.RootConfig;
import entity.QiniuEntity;
import mapper.CourseMapper;
import mapper.JobMapper;
import mapper.JobSubmitItemMapper;
import mapper.QiniuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.QiniuUtil;

import java.io.File;
import java.io.UnsupportedEncodingException;

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
    private QiniuMapper qiniuMapper;
    private JobSubmitItemMapper jobSubmitItemMapper;
    @Autowired
    public void setJobSubmitItemMapper(JobSubmitItemMapper jobSubmitItemMapper) {
        this.jobSubmitItemMapper = jobSubmitItemMapper;
    }
    @Autowired
    public void setQiniuMapper(QiniuMapper qiniuMapper) {
        this.qiniuMapper = qiniuMapper;
    }

    @Autowired
    public void setCourseMapper(CourseMapper mapper) {
        this.courseMapper = mapper;
    }

    @Autowired
    public void setJobMapper(JobMapper jobMapper) {
        this.jobMapper = jobMapper;
    }

    @Test
    public void testJob() throws UnsupportedEncodingException {
//        jobMapper.jobItemSubmit(7,"test",1);
        jobSubmitItemMapper.jobItemDelete(6,"Diagram 1.svg",2);
    }
    @Test
    public void testQiniu(){
    }
    @Test
    public void testCourse() {

    }
}
