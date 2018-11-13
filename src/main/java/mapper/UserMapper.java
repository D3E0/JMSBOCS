package mapper;

import entity.UserEntity;

public interface UserMapper {
    UserEntity getUser(String username);
}
