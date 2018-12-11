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
import service.UserService;

import java.util.HashSet;
import java.util.Set;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {RootConfig.class})
public class UserMapperTest {

    @Autowired
    private UserMapper mapper;
    @Autowired
    private UserService service;

    @Test
    public void saveMulti() {
        Set<UserSDTO> list = new HashSet<UserSDTO>();
        for (int i = 1160299001; i <= 1160299015; i++) {
            UserSDTO user = new UserSDTO(i, "张三", "软工161");
            list.add(user);
        }
        int res = service.saveStuSet(list);
        System.out.println(res);
    }

    @Test
    public void save() {
        UserEntity entity = new UserEntity();
        entity.setUserId(1160299001);
        entity.setUsername("李四");
        int res = mapper.save(entity);
        System.out.println(res);
        System.out.println(entity.getUserId());
    }
}
