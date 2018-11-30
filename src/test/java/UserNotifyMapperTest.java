import config.RootConfig;
import dto.NotifyDTO;
import entity.UserNotifyEntity;
import manager.NotifyManager;
import mapper.UserNotifyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class UserNotifyMapperTest {

    @Autowired
    private UserNotifyMapper mapper;

    @Autowired
    NotifyManager manager;


    @Test
    public void save() {
        UserNotifyEntity entity = new UserNotifyEntity();
        entity.setNotifyId(1);
        entity.setUserId(1);
        int res = mapper.save(entity);
        System.out.println(res);
    }

    @Test
    public void select() {
//        UserNotifyEntity entity = mapper.selectOne(1);
//        System.out.println(entity);
//        entity.setRead(true);
//        int res = mapper.update(entity);
//        System.out.println(res);
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        List<NotifyDTO> list = mapper.select(2);
        System.out.println(list);
    }

    @Test
    public void delete() {
        int res = mapper.delete(1);
        System.out.println(res);
    }
}
