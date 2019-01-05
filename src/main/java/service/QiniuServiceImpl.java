package service;

import entity.QiniuEntity;
import mapper.QiniuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author yan
 * @date 2018/11/30 16:43
 * @descripition
 */
@Service
public class QiniuServiceImpl implements QiniuService {

    private final QiniuMapper mapper;

    @Autowired
    public QiniuServiceImpl(QiniuMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public int save(QiniuEntity entity) {
        int res = mapper.save(entity);
        if (res > 0) {
            return entity.getQiniuId();
        }
        return res;
    }
}
