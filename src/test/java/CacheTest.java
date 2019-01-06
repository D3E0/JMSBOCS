import config.CacheConfig;
import config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.FileService;

/**
 * @author yan
 * @date 2018/11/24 11:57
 * @descripition
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class, CacheConfig.class})
public class CacheTest {
    @Autowired
    private FileService fileService;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Test
    public void  fileService() throws InterruptedException {
        System.out.println(passwordEncoder.encode("wang123"));
        System.out.println(passwordEncoder.encode("888888"));
//        System.out.println(passwordEncoder.matches(zhao123,passwordEncoder.encode("zhao123")));
//        fileService.getUploadToken(2);
//        fileService.getUploadToken(2);
//        Thread.sleep(1000);
//        fileService.getUploadToken(2);
//        fileService.getUploadToken(2);
    }
}
