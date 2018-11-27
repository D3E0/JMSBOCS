package mapper;

import dto.JobItemDTO;
import entity.JobEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 11:15
 * @descripition
 */
@Component
public interface JobMapper extends BaseMapper<JobEntity>{
    List<JobItemDTO> findJobListByIdAndKeyword(@Param("studentId") int studentId, @Param("cur")int cur,@Param("keyword") String keyword);
    JobItemDTO findJobById(int jobId);
    int countJobByKeyword(@Param("studentId")int studentId,@Param("keyword") String keyword);
}
