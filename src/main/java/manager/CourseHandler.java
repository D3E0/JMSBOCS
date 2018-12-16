package manager;

import dto.UserSDTO;
import entity.NotifyEntity;
import entity.NotifyType;
import entity.UserNotifyEntity;
import mapper.UserNotifyMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import service.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author ACM-PC
 */
@Component
public class CourseHandler implements NotifyHandler {
    private final CourseService courseService;
    private final UserNotifyMapper mapper;
    private static final Logger logger = LogManager.getLogger(CourseHandler.class);

    @Autowired
    public CourseHandler(CourseService courseService, UserNotifyMapper mapper) {
        this.courseService = courseService;
        this.mapper = mapper;
    }

    @Override
    public boolean doHandler(NotifyEntity entity) {
        NotifyType type = entity.getNotifyType();
        if (!type.equals(NotifyType.COURSEWIDE)) {
            return false;
        }
        Set<UserSDTO> receivers = courseService.selectUserSet(entity.getNotifyReceiver());
        List<UserNotifyEntity> entities = new ArrayList<>();
        for (UserSDTO receiver : receivers) {
            entities.add(new UserNotifyEntity(entity.getNotifyId(), receiver.getUserId()));
        }
        logger.info(String.format("to add %s notify %d to %d user", type, entity.getNotifyId(), entities.size()));
        if (entities.size() > 0) {
            int res = mapper.saveList(entities);
            logger.info(String.format("add notify %d to %d user", entity.getNotifyId(), res));
            return res != 0;
        }
        return true;
    }

}
