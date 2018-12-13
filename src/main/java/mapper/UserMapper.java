package mapper;

import dto.UserDTO;
import entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<UserEntity> {
    /**
     * 查询用户详细信息 七牛云
     *
     * @param id
     * @return
     */
    UserDTO selectUserDTO(int id);

//    TODO 拦截器

    /**
     * 批量保存用户  有则忽略 无则添加  size > 0
     *
     * @param list
     * @return
     */
    int saveUserListIgnore(List<UserEntity> list);
}
