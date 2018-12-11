import config.RootConfig;
import dto.UserSDTO;
import entity.UserEntity;
import entity.UserType;
import mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;

    @Test
    public void save() {
        UserSDTO user = new UserSDTO(1160299042, "张三", "软工161");
        UserEntity entity = new UserEntity();
//        entity.setUserId(1160299031);
        entity.setUsername("李四");
        entity.setType(UserType.STUDENT);
        int res = mapper.save(entity);
        System.out.println(res);
        System.out.println(entity.getUserId());
    }
}
