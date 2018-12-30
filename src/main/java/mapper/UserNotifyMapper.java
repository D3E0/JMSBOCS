package mapper;

import dto.NotifyDTO;
import entity.UserNotifyEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserNotifyMapper extends BaseMapper<UserNotifyEntity> {

    /**
     * 获取用户消息列表
     *
     * @param id
     * @return
     */
    List<NotifyDTO> select(int id);

    List<NotifyDTO> selectLimit(@Param("id") int userId, @Param("start") int start, @Param("offset") int offset);

    Long getCount(int userId);

    /**
     * 批量保存 消息
     *
     * @param list
     * @return
     */
    int saveList(List<UserNotifyEntity> list);
}
