package service;

import dto.NotifyDTO;
import mapper.NotifyMapper;
import mapper.UserNotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotifyServiceImpl implements NotifyService {
    private final NotifyMapper notifyMapper;
    private final UserNotifyMapper userNotifyMapper;

    @Autowired
    public NotifyServiceImpl(NotifyMapper notifyMapper, UserNotifyMapper userNotifyMapper) {
        this.notifyMapper = notifyMapper;
        this.userNotifyMapper = userNotifyMapper;
    }

    public List<NotifyDTO> selectUserNotifyList(int userId) {
        return userNotifyMapper.select(userId);
    }

    public List<NotifyDTO> selectUserNotifyList(int userId, int page, int limit) {
        return userNotifyMapper.selectLimit(userId, (page - 1) * limit, limit);
    }

    public Long getCount(int userId) {
        return userNotifyMapper.getCount(userId);
    }

    public int deleteUserNotify(int id) {
        return userNotifyMapper.delete(id);
    }

}
