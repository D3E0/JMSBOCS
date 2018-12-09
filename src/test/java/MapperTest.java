import com.qiniu.util.Auth;
import com.sun.deploy.net.URLEncoder;
import config.RootConfig;
import dto.JobItemDTO;
import entity.JobEntity;
import entity.QiniuEntity;
import mapper.CourseMapper;
import mapper.JobMapper;
import mapper.QiniuMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import util.QiniuUtil;

import java.io.UnsupportedEncodingException;
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
    private QiniuMapper qiniuMapper;
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
        jobMapper.jobItemSubmit(7,"test",1);
    }
    @Test
    public void testQiniu(){
        QiniuEntity qiniuEntity=qiniuMapper.getQiniuByCourseId(1);
        QiniuUtil.queryDomain(qiniuEntity);
    }
    @Test
    public void testCourse() {

    }
}
