package mapper;

import entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper {
    UserEntity getUser(String username);
}
