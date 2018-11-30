package manager;

import entity.NotifyEntity;
import entity.NotifyType;
import mapper.NotifyMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class NotifyManager {
    private static final int CAPACITY = 100;
    private static final Logger logger = LogManager.getLogger(NotifyManager.class);
    private final BlockingQueue<NotifyEntity> notifyQueue;
    private final NotifyMapper mapper;
    private NotifyConsumer consumer;
    private Map<NotifyType, NotifyHandler> handlerMap;
    private int status = 0;

    public NotifyManager(NotifyMapper mapper, DeafultHandler deafultHandler) {
        notifyQueue = new ArrayBlockingQueue<NotifyEntity>(CAPACITY);
        this.mapper = mapper;
        handlerMap = new ConcurrentHashMap<NotifyType, NotifyHandler>();
        for (NotifyType type : NotifyType.values()) {
            handlerMap.put(type, deafultHandler);
        }
    }

    public boolean registerHandler(NotifyHandler handler, NotifyType type) {
        boolean res = false;
        if (status == 0) {
            handlerMap.put(type, handler);
            res = true;
        }
        logger.info(String.format("Associates %s with %s %s", handler.getClass().getSimpleName(), type, res));
        return res;
    }

    private void init() {
        consumer = new NotifyConsumer();
        List<NotifyEntity> list = mapper.getUnfinished();
        logger.info(String.format("Add {%d} unfinished task to queue ", list == null ? 0 : list.size()));
        notifyQueue.addAll(mapper.getUnfinished());
    }

    @PostConstruct
    public synchronized void start() {
        if (status != 0) {
            return;
        }
        status = 1;
        logger.info("Notify Manager start");
        init();
        consumer.start();
    }

    public synchronized void stop() {
        consumer.interrupt();
        status = 0;
        notifyQueue.clear();
        logger.info("Notify Manager stop, remove all task");
    }

    public boolean add(NotifyEntity entity) {
        boolean res = notifyQueue.add(entity);
        logger.info(String.format("add task {%d, %s} %s", entity.getNotifyId(), entity.getNotifyType(), res));
        return res;
    }

    class NotifyConsumer extends Thread {
        @Override
        public void run() {
            logger.info("Thread consumer start......");
            for (; ; ) {
                try {
                    NotifyEntity entity = notifyQueue.take();
                    NotifyType type = entity.getNotifyType();
                    NotifyHandler handler = handlerMap.get(type);
                    logger.info(String.format("process notify{%d, %s} with %s", entity.getNotifyId(), type, handler.getClass().getSimpleName()));
                    boolean res = handler.doHandler(entity);
                    if (res) {
                        entity.setFinished(true);
                        mapper.update(entity);
                    }
                    logger.info(String.format("notify{%d, %s} process result %s", entity.getNotifyId(), type, res));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    logger.info("Thread consumer interrupt");
                    break;
                }
            }
        }
    }
}
