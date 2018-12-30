package manager;

import entity.NotifyEntity;
import entity.NotifyType;
import entity.UserNotifyEntity;
import mapper.UserNotifyMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DefaultHandler implements NotifyHandler {

    private final UserNotifyMapper mapper;

    @Autowired
    public DefaultHandler(UserNotifyMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public boolean doHandler(NotifyEntity entity) {
        boolean res = false;
        NotifyType type = entity.getNotifyType();
        if (type.equals(NotifyType.PEERTOPEER)) {
            UserNotifyEntity x = new UserNotifyEntity(entity.getNotifyId(), entity.getNotifyReceiver());
            res = mapper.save(x) > 0;
        }
        return res;
    }
}
