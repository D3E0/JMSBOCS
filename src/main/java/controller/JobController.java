package controller;

import dto.JobItemDTO;
import dto.JobSubmitRecordDTO;
import dto.JobSubmitRecordNumber;
import entity.JobEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.FileService;
import service.JobService;
import vo.JobSubmitRecordVO;
import vo.JobVO;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    private FileService fileService;

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) throws Exception {
        binder.registerCustomEditor(Date.class, new CustomDateEditor(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"), true));
    }

    @RequestMapping(value = "jobList", method = RequestMethod.GET)
    public String jobList() {
        return "jobList";
    }

    @ResponseBody
    @RequestMapping(value = "jobList", method = RequestMethod.POST)
    public List<JobItemDTO> jobList(@RequestParam int studentId, @RequestParam int page, @RequestParam(defaultValue = "") String keyword) {
        return jobService.findJobListById(studentId, page, keyword);
    }

    @RequestMapping("job")
    public String job(Model model, int jobId, HttpSession session) {
        model.addAttribute("job", jobService.findJobById(jobId));
        model.addAttribute("jobId", jobId);
        model.addAttribute("filePrefix",fileService.findFilePrefixByJobId(jobId).getFilePerfix());
        return "job";
    }

    @ResponseBody
    @RequestMapping("countJob")
    public int countJob(int studentId, @RequestParam(defaultValue = "") String keyword) {
        return jobService.countJob(studentId, keyword);
    }

    @RequestMapping(value = "addJob", method = RequestMethod.GET)
    public String addJob(int jobId) {
        return "job";
    }

    @ResponseBody
    @RequestMapping(value = "deleteJob", method = RequestMethod.POST)
    public void deleteJob(int jobId) {
        jobService.deleteJob(jobId);
    }

    @RequestMapping(value = "updateJob", method = RequestMethod.GET)
    public String updateJobView(Model model, JobVO jobVO) {
        model.addAttribute("job", jobVO);
        return "updateJob";
    }

    @ResponseBody
    @RequestMapping(value = "updateJob", method = RequestMethod.POST)
    public void updateJob(JobVO jobVO) {
        logger.info(jobVO.toString());
        jobService.updateJob(new JobEntity(jobVO));
    }

    @ResponseBody
    @RequestMapping(value = "jobItemSubmit", method = RequestMethod.POST)
    public void jobItemSubmit(int jobId, String fileName, int userId) {
        jobService.jobItemSubmit(jobId, userId, fileName);
    }

    @RequestMapping(value = "jobSubmitRecord", method = RequestMethod.GET)
    public String jobSubmitRecordView(Model model, int jobId) {
        JobSubmitRecordNumber jobSubmitRecordNumber = jobService.countJobSubmitRecordNum(jobId);
        model.addAttribute("jobId", jobId);
        model.addAttribute("need", jobSubmitRecordNumber.getNeed());
        model.addAttribute("already", jobSubmitRecordNumber.getAlready());
        return "jobSubmitRecord";
    }

    @ResponseBody
    @RequestMapping(value = "jobSubmitRecord", method = RequestMethod.POST)
    public JobSubmitRecordVO jobSubmitRecord(int jobId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "20") int limit) {
        List<JobSubmitRecordDTO> data = jobService.getJobSubmitRecord(jobId, page, keyword, limit);
        int count = jobService.countJobSubmitRecord(jobId, keyword);
        return new JobSubmitRecordVO(data, 0, "", count);
    }

    @ResponseBody
    @RequestMapping(value = "jobSubmitRecordNum", method = RequestMethod.POST)
    public JobSubmitRecordNumber jobSubmitRecordNum(int jobId) {
        return jobService.countJobSubmitRecordNum(jobId);
    }
}
