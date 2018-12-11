package mapper;

import entity.NotifyEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotifyMapper extends BaseMapper<NotifyEntity> {

    List<NotifyEntity> getUnfinished();

}
