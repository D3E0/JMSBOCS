package controller;

import dto.JobSubmitRecordDTO;
import dto.JobSubmitRecordNumber;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FileService;
import service.JobService;
import service.JobSubmitService;
import vo.JobSubmitRecordVO;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/11 17:04
 * @descripition
 */
@Controller
public class JobSubmitController {
    private JobService jobService;
    private JobSubmitService jobSubmitService;
    private FileService fileService;
    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }

    @Autowired
    public void setJobSubmitService(JobSubmitService jobSubmitService) {
        this.jobSubmitService = jobSubmitService;
    }


    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @ResponseBody
    @RequestMapping(value = "jobItemSubmit", method = RequestMethod.POST)
    public void jobItemSubmit(int jobId, String fileName, int userId) {
        jobSubmitService.jobItemSubmit(jobId, userId, fileName);
    }

    @RequestMapping(value = "jobSubmitRecord", method = RequestMethod.GET)
    public String jobSubmitRecordView(Model model, int jobId) {
        JobSubmitRecordNumber jobSubmitRecordNumber = jobSubmitService.countJobSubmitRecordNum(jobId);
        model.addAttribute("jobId", jobId);
        model.addAttribute("need", jobSubmitRecordNumber.getNeed());
        model.addAttribute("already", jobSubmitRecordNumber.getAlready());
        return "jobSubmitRecord";
    }

    @ResponseBody
    @RequestMapping(value = "jobSubmitRecord", method = RequestMethod.POST)
    public JobSubmitRecordVO jobSubmitRecord(int jobId, @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "") String keyword, @RequestParam(defaultValue = "20") int limit) {
        List<JobSubmitRecordDTO> data = jobSubmitService.getJobSubmitRecord(jobId, page, keyword, limit);
        int count = jobSubmitService.countJobSubmitRecord(jobId, keyword);
        return new JobSubmitRecordVO(data, 0, "", count);
    }

    @ResponseBody
    @RequestMapping(value = "jobSubmitRecordNum", method = RequestMethod.POST)
    public JobSubmitRecordNumber jobSubmitRecordNum(int jobId) {
        return jobSubmitService.countJobSubmitRecordNum(jobId);
    }
    @RequestMapping(value = "jobOfficePreview", method = RequestMethod.GET)
    public String jobOfficePreview(Model model, int jobId) {
        return "preview";
    }
}
