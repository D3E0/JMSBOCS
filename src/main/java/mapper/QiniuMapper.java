package mapper;

import entity.JobEntity;
import entity.QiniuEntity;
import org.springframework.stereotype.Component;

/**
 * @author yan
 * @date 2018/11/24 11:15
 * @descripition
 */
@Component
public interface QiniuMapper extends BaseMapper<QiniuEntity>{
    QiniuEntity getQiniuByCourseId(int courseId);
}
