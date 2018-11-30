package manager;

import entity.NotifyEntity;

public interface NotifyHandler {
    boolean doHandler(NotifyEntity entity);
}
