package mapper;

import dto.JobSubmitRecordDTO;
import dto.JobSubmitRecordNumber;
import entity.JobEntity;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/10 11:49
 * @descripition
 */
@Repository
public interface JobSubmitItemMapper extends BaseMapper<JobEntity>{
    void jobItemSubmit(@Param("jobId")int jobId, @Param("filename")String filename, @Param("userId")int userId);
    int isSameFile(@Param("jobId")int jobId,@Param("filename")String filename,@Param("userId")int userId);
    void jobItemDelete(@Param("jobId")int jobId,@Param("fileName")String fileName,@Param("userId")int userId);
    List<JobSubmitRecordDTO> getJobSubmitRecord(@Param("jobId")int jobId, @Param("cur")int cur, @Param("keyword") String keyword, @Param("limit")int limit);
    int countJobSubmitRecord(@Param("jobId")int jobId,@Param("keyword") String keyword);
    JobSubmitRecordNumber countJobSubmitRecordNum(@Param("jobId")int jobId);
}
