package service;

import dto.UserDTO;
import dto.UserSDTO;
import entity.UserEntity;

import java.util.Set;

public interface UserService extends BaseService<UserEntity> {
    UserEntity processLogin(Integer id, String password);

    /**
     * 查询用户详情 七牛云
     *
     * @param id
     * @return
     */
    UserDTO selectUserDTO(int id);

    /**
     * 批量保存教师  有则忽略 无则添加
     *
     * @param set
     * @return
     */
    int saveTchSet(Set<UserSDTO> set);

    /**
     * 批量保存学生  有则忽略 无则添加
     *
     * @param set
     * @return
     */
    int saveStuSet(Set<UserSDTO> set);

}
