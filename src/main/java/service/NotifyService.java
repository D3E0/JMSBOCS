package service;

import dto.NotifyDTO;
import mapper.NotifyMapper;
import mapper.UserNotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyService {
    private final NotifyMapper mapper;
    private final UserNotifyMapper userNotifyMapper;

    @Autowired
    public NotifyService(NotifyMapper mapper, UserNotifyMapper userNotifyMapper) {
        this.mapper = mapper;
        this.userNotifyMapper = userNotifyMapper;
    }

    public List<NotifyDTO> selectUserNotifyList(int userId) {
        return userNotifyMapper.select(userId);
    }

    public int deleteUserNotify(int id) {
        return userNotifyMapper.delete(id);
    }

}
