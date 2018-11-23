package mapper;

import entity.UserEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserMapper {
    UserEntity getUser(String username);

    List<UserEntity> getUserList();
}
