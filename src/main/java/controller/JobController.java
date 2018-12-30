package controller;

import dto.JobItemDTO;
import entity.JobEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import service.CourseService;
import service.FileService;
import service.JobService;
import util.UserSecurity;
import vo.AddJobVO;
import vo.UpdateJobVO;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
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
    private CourseService courseService;
    @Autowired
    public void setCourseService(CourseService courseService) {
        this.courseService = courseService;
    }

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
    public List<JobItemDTO> jobList( @RequestParam int page, @RequestParam(defaultValue = "") String keyword) {
        int userId= UserSecurity.getId();
        return jobService.findJobListById(userId, page, keyword);
    }

    @RequestMapping(value = "job",method = RequestMethod.GET)
    public String job(Model model, int jobId, HttpSession session) {
        int userId= UserSecurity.getId();
        logger.info(jobService.findJobById(jobId).toString());
        model.addAttribute("job", jobService.findJobById(jobId));
        model.addAttribute("jobId", jobId);
        model.addAttribute("userId", userId);
        model.addAttribute("filePrefix",fileService.findFilePrefixByJobId(jobId).getFilePrefix());
        return "job";
    }
    @ResponseBody
    @RequestMapping(value = "getJobContent",method = RequestMethod.POST)
    public String job(int jobId) {
        return jobService.findJobById(jobId).getJobContent();
    }

    @ResponseBody
    @RequestMapping("countJob")
    public int countJob(@RequestParam(defaultValue = "") String keyword) {
        int userId= UserSecurity.getId();
        return jobService.countJob(userId, keyword);
    }

    @RequestMapping(value = "addJob", method = RequestMethod.GET)
    public String addJob(Model model) {
        int userId= UserSecurity.getId();
        model.addAttribute("courseList",courseService.selectCourseDTOListTch(userId));
        return "addJob";
    }
    @ResponseBody
    @RequestMapping(value = "addJob", method = RequestMethod.POST)
    public void addJob(Model model, AddJobVO addJobVO) {
        logger.info(addJobVO.toString());
        jobService.addJob(new JobEntity(addJobVO));
    }
    @ResponseBody
    @RequestMapping(value = "isSameJobTitle", method = RequestMethod.POST)
    public int isSameJobTitle(Model model,int courseId,String jobTitle) {
        return jobService.isSameJobTitle(courseId,jobTitle);
    }

    @ResponseBody
    @RequestMapping(value = "deleteJob", method = RequestMethod.POST)
    public void deleteJob(int jobId) {
        jobService.deleteJob(jobId);
    }

    @RequestMapping(value = "updateJob", method = RequestMethod.GET)
    public String updateJobView(Model model, UpdateJobVO updateJobVO) {
        model.addAttribute("job", updateJobVO);
        model.addAttribute("courseId", jobService.findJobById(updateJobVO.getJobId()).getCourseId());
        return "updateJob";
    }

    @ResponseBody
    @RequestMapping(value = "updateJob", method = RequestMethod.POST)
    public int updateJob(UpdateJobVO updateJobVO) {
        logger.info(updateJobVO.toString());
        try {
            jobService.updateJob(new JobEntity(updateJobVO));
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
        return 1;
    }
}
