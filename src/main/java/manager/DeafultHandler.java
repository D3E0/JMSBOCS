package manager;

import entity.NotifyEntity;
import entity.NotifyType;
import entity.UserNotifyEntity;
import mapper.UserNotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeafultHandler implements NotifyHandler {

    private final UserNotifyMapper mapper;

    @Autowired
    public DeafultHandler(UserNotifyMapper mapper) {
        this.mapper = mapper;
    }

    public boolean doHandler(NotifyEntity entity) {
        boolean res = false;
        NotifyType type = entity.getNotifyType();
        if (type == NotifyType.COMMENTREPLY || type == NotifyType.COMMENTUPVOTE || type == NotifyType.COURSEADD) {
            UserNotifyEntity x = new UserNotifyEntity(entity.getNotifyId(), entity.getNotifyReceiver());
            res = mapper.save(x) > 0;
        }
        return res;
    }
}
