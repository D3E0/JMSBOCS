package service;

import com.qiniu.common.QiniuException;
import com.qiniu.http.Client;
import com.qiniu.http.Response;
import com.qiniu.util.Auth;
import com.qiniu.util.StringMap;
import dto.UserDTO;
import entity.UserEntity;
import mapper.UserMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
    private final UserMapper mapper;

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
