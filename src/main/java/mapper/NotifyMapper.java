package mapper;

import entity.NotifyEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface NotifyMapper extends BaseMapper<NotifyEntity> {

    List<NotifyEntity> getUnfinished();

}
