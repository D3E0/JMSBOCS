package manager;

import entity.NotifyEntity;
import entity.NotifyType;
import mapper.NotifyMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author ACM-PC
 */
public class NotifyManager {
    private static final int CAPACITY = 100;
    private static final Logger logger = LogManager.getLogger(NotifyManager.class);
    private final BlockingQueue<NotifyEntity> notifyQueue;
    private final NotifyMapper mapper;
    private NotifyConsumer consumer;
    private Map<NotifyType, NotifyHandler> handlerMap;
    private int status = 0;

    public NotifyManager(NotifyMapper mapper) {
        notifyQueue = new ArrayBlockingQueue<>(CAPACITY);
        this.mapper = mapper;
        handlerMap = new ConcurrentHashMap<>();
    }

    /**
     * 注册 NotifyHandler
     *
     * @param handler
     * @param type
     */
    public void registerHandler(NotifyHandler handler, NotifyType type) {
        boolean res = false;
        if (status == 0) {
            handlerMap.put(type, handler);
            res = true;
        }
        logger.info(String.format("Associates %s with %s %s", handler.getClass().getSimpleName(), type, res));
    }

    /**
     * 初始化阻塞队列，添加未完成的任务到队列
     */
    private void init() {
        consumer = new NotifyConsumer();
        List<NotifyEntity> list = mapper.getUnfinished();
        logger.info(String.format("Add unfinished task to queue ==> %d", list == null ? 0 : list.size()));
        notifyQueue.addAll(mapper.getUnfinished());
    }

    /**
     * 容器启动时，开启消费者线程
     */
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

    /**
     * 容器关闭时，中断消费者线程
     */
    @PreDestroy
    public synchronized void stop() {
        consumer.interrupt();
        status = 0;
        notifyQueue.clear();
        logger.info("Notify manager stop, remove all task");
    }

    public boolean add(NotifyEntity entity) {
        if (entity == null) {
            return false;
        }
        int result = mapper.save(entity);
        if (result == 0) {
            return false;
        }
        boolean res = notifyQueue.add(entity);
        logger.info(String.format("add task %d, %s ==> %s", entity.getNotifyId(), entity.getNotifyType(), res));
        return res;
    }

    class NotifyConsumer extends Thread {
        @Override
        public void run() {
            logger.info("Thread consumer start......");
            for (; ; ) {
                NotifyEntity entity;
                try {
                    entity = notifyQueue.take();
                } catch (InterruptedException e) {
                    // 响应中断请求
                    logger.info("Thread consumer interrupt");
                    break;
                }
                NotifyType type = entity.getNotifyType();
                NotifyHandler handler = handlerMap.get(type);
                if (handler == null) {
                    logger.info(String.format("notify %d, %s associates handle ==> null", entity.getNotifyId(), type));
                    continue;
                }
                logger.info(String.format("notify %d, %s associates handle ==> %s", entity.getNotifyId(), type, handler.getClass().getSimpleName()));
                boolean res = handler.doHandler(entity);
                if (res) {
                    entity.setFinished(true);
                    mapper.update(entity);
                }
                logger.info(String.format("notify %d, %s process result ==> %s", entity.getNotifyId(), type, res));
            }
        }
    }
}
