package service;

import dto.UserDTO;
import dto.UserSDTO;
import entity.UserEntity;
import entity.UserType;
import mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserMapper mapper;
    public static final String PWD = "888888";

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    public UserEntity processLogin(Integer id, String password) {
        UserEntity entity = selectOne(id);
        if (entity != null && entity.getPassword().equals(password)) {
            entity.setLastLoginTime(new Date());
            update(entity);
        }
        return entity;
    }

    public UserDTO selectUserDTO(int id) {
        return mapper.selectUserDTO(id);
    }

    public int saveTchSet(Set<UserSDTO> set) {
        List<UserEntity> list = new ArrayList<UserEntity>();
        for (UserSDTO user : set) {
            UserEntity entity = new UserEntity(user.getUserId(), user.getUsername(), UserType.TEACHER);
            entity.setPassword(PWD);
            list.add(entity);
        }
        return mapper.saveUserListIgnore(list);
    }

    public int saveStuSet(Set<UserSDTO> set) {
        List<UserEntity> list = new ArrayList<UserEntity>();
        for (UserSDTO user : set) {
            UserEntity entity = new UserEntity(user.getUserId(), user.getUsername(), UserType.STUDENT);
            entity.setSpecialty(user.getSpecialty());
            entity.setPassword(PWD);
            list.add(entity);
        }
        return mapper.saveUserListIgnore(list);
    }

    public int update(UserEntity entity) {
        return mapper.update(entity);
    }

    public int delete(int id) {
        return mapper.delete(id);
    }

    public int save(UserEntity entity) {
        return mapper.save(entity);
    }

    public UserEntity selectOne(int id) {
        return mapper.selectOne(id);
    }

    public List<UserEntity> selectAll() {
        return mapper.selectAll();
    }
}
