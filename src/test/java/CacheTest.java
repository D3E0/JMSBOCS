import config.CacheConfig;
import config.RootConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.FileService;
import util.QiniuUtil;

import java.io.File;

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
    @Test
    public void  fileService() throws InterruptedException {
        fileService.getUploadToken(2);
        fileService.getUploadToken(2);
        Thread.sleep(1000);
        fileService.getUploadToken(2);
        fileService.getUploadToken(2);
    }
}
