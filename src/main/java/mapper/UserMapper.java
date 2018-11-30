package mapper;

import entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<UserEntity> {
    UserEntity select(String username);

}
