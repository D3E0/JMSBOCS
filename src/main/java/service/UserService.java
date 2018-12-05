package service;

import dto.UserDTO;
import entity.UserEntity;

public interface UserService extends BaseService<UserEntity> {
    UserEntity processLogin(Integer id, String password);

    UserDTO selectUserDTO(int id);

}
