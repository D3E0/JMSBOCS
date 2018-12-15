package service;

import dto.UserDTO;
import dto.UserSDTO;
import entity.UserEntity;
import entity.UserType;
import dto.UserInfo;
import mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author ACM-PC
 */
@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserMapper mapper;
    private static final String PWD = "888888";

    @Autowired
    public UserServiceImpl(UserMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public UserEntity processLogin(Integer id, String password) {
        UserEntity entity = selectOne(id);
        if (entity != null && entity.getPassword().equals(password)) {
            entity.setLastLoginTime(new Date());
            update(entity);
        }
        return entity;
    }

    @Override
    public UserDTO selectUserDTO(int id) {
        return mapper.selectUserDTO(id);
    }

    @Override
    public int saveTchSet(Set<UserSDTO> set) {
        List<UserEntity> list = new ArrayList<UserEntity>();
        for (UserSDTO user : set) {
            UserEntity entity = new UserEntity(user.getUserId(), user.getUsername(), UserType.TEACHER);
            entity.setPassword(PWD);
            list.add(entity);
        }
        logger.info(String.format("to add %d tch to DB ==> %s", list.size(), list));
        if (list.size() == 0) {
            return 0;
        }
        int res = mapper.saveUserListIgnore(list);
        logger.info("add " + res + " tch to database");
        return res;
    }

    @Override
    public int saveStuSet(Set<UserSDTO> set) {
        List<UserEntity> list = new ArrayList<UserEntity>();
        for (UserSDTO user : set) {
            UserEntity entity = new UserEntity(user.getUserId(), user.getUsername(), UserType.STUDENT);
            entity.setSpecialty(user.getSpecialty());
            entity.setPassword(PWD);
            list.add(entity);
        }
        logger.info(String.format("to add %d stu to DB ==> %s", list.size(), list));
        if (list.size() == 0) {
            return 0;
        }
        int res = mapper.saveUserListIgnore(list);
        logger.info("add " + res + " stu to database");
        return res;
    }

    @Override
    public UserInfo selectUserInfo(int id) {
        return mapper.selectUserInfo(id);
    }

    @Override
    public int update(UserEntity entity) {
        return mapper.update(entity);
    }

    @Override
    public int delete(int id) {
        return mapper.delete(id);
    }

    @Override
    public int save(UserEntity entity) {
        return mapper.save(entity);
    }

    @Override
    public UserEntity selectOne(int id) {
        return mapper.selectOne(id);
    }

    @Override
    public List<UserEntity> selectAll() {
        return mapper.selectAll();
    }
}
