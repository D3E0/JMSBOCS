import config.RootConfig;
import entity.NotifyEntity;
import entity.NotifyType;
import mapper.NotifyMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class NotifyMapperTest {

    @Autowired
    private NotifyMapper mapper;


    @Test
    public void save() {
        NotifyEntity entity = new NotifyEntity();
        entity.setNotifyContent("课程作业提交提醒");
        entity.setNotifyTime(new Date());
        entity.setNotifySender(1);
//        entity.setNotifyType(NotifyType.COMMENTREPLY);
        entity.setNotifyReceiver(2);
        entity.setFinished(true);
        int res = mapper.save(entity);
        System.out.println(res);
    }

    @Test
    public void select() {
        NotifyEntity entity = mapper.selectOne(2);
//        System.out.println(entity.getNotifyType().equals(NotifyType.COMMENTREPLY));
        System.out.println(entity);

        List<NotifyEntity> entities = mapper.getUnfinished();
        System.out.println(entities);
    }


}
