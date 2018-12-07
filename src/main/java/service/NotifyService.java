package service;


import dto.NotifyDTO;

import java.util.List;

public interface NotifyService {
    List<NotifyDTO> selectUserNotifyList(int userId);

    List<NotifyDTO> selectUserNotifyList(int userId, int page, int limit);

    Long getCount(int userId);

    int deleteUserNotify(int id);
}
