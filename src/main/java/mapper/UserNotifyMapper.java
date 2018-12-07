package mapper;

import dto.NotifyDTO;
import entity.UserNotifyEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface UserNotifyMapper extends BaseMapper<UserNotifyEntity> {
    List<UserNotifyEntity> selectUnread(int id);

    List<NotifyDTO> select(int id);

    List<NotifyDTO> selectLimit(@Param("id") int userId, @Param("start") int start, @Param("offset") int offset);

    Long getCount(int userId);
}
