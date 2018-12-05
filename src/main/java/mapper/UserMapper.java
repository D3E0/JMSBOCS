package mapper;

import dto.UserDTO;
import entity.UserEntity;
import org.springframework.stereotype.Component;

@Component
public interface UserMapper extends BaseMapper<UserEntity> {
    UserDTO selectUserDTO(int id);
}
