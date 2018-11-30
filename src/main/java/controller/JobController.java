package controller;

import com.qiniu.storage.model.FileInfo;
import dto.JobItemDTO;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.JobService;
import service.QiniuService;

import java.util.List;

/**
 * @author yan
 * @date 2018/11/24 12:13
 * @descripition
 */
@Controller
public class JobController {
    private static final Logger logger = LogManager.getLogger(JobController.class);
    private JobService jobService;
    private QiniuService qiniuService;
    @Autowired
    public void setQiniuService(QiniuService qiniuService) {
        this.qiniuService = qiniuService;
    }

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @RequestMapping(value = "joblist", method = RequestMethod.GET)
    public String jobList() {
        return "joblist";
    }

    @ResponseBody
    @RequestMapping(value = "joblist", method = RequestMethod.POST)
    public List<JobItemDTO> jobList(@RequestParam int studentid,@RequestParam int page, @RequestParam(defaultValue = "") String keyword) {
        return jobService.findJobListById(1, page, keyword);
    }

    @RequestMapping("job")
    public String job(Model model, int jobId) {
        model.addAttribute("job", jobService.findJobById(jobId));
        return "job";
    }

    @ResponseBody
    @RequestMapping("countjob")
    public int countJob(int studentid,@RequestParam(defaultValue = "") String keyword) {
        return jobService.countJob(1,keyword);
    }

    @RequestMapping(value = "addjob", method = RequestMethod.GET)
    public String addjob(int jobId) {
        return "job";
    }
    @ResponseBody
    @RequestMapping(value = "qiniu")
    public String getqiniu(int courseId){
        return qiniuService.getUploadToken(courseId);
    }
    @ResponseBody
    @RequestMapping(value = "uploadfiles")
    public FileInfo[] getuploadfiles(int courseId,int jobId,int studentId){
        return qiniuService.getFileList(courseId,jobId,studentId);
    }
    @RequestMapping(value = "jobfilelist", method = RequestMethod.GET)
    public String jobfilelist() {
        return "jobfilelist";
    }

    @ResponseBody
    @RequestMapping(value = "domain")
    public String getdomain(int courseId){
        return qiniuService.queryDomain(courseId);
    }
}
