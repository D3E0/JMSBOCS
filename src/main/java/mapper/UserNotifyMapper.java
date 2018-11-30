package mapper;

import dto.NotifyDTO;
import entity.UserNotifyEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserNotifyMapper extends BaseMapper<UserNotifyEntity> {
    List<UserNotifyEntity> selectUnread(int id);

    List<NotifyDTO> select(int id);
}
