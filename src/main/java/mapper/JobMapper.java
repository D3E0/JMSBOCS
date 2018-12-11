package mapper;

import dto.JobItemDTO;
import entity.JobEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 11:15
 * @descripition
 */
@Repository
public interface JobMapper extends BaseMapper<JobEntity>{
    List<JobItemDTO> findJobListForStudent(@Param("studentId") int studentId, @Param("cur")int cur,@Param("keyword") String keyword);
    JobItemDTO findJobById(int jobId);
    JobEntity findById(int jobId);
    int countJobForStudent(@Param("studentId")int studentId,@Param("keyword") String keyword);
    void deleteJob(@Param("jobId")int jobId);
    void updateJob(@Param("job")JobEntity job);
    void addJob(@Param("job")JobEntity job);
    List<JobItemDTO> findJobListForTeacher(@Param("teacherId") int teacherId, @Param("cur")int cur,@Param("keyword") String keyword);
    int countJobForTeacher(@Param("teacherId")int teacherId,@Param("keyword") String keyword);
}
