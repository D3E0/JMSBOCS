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
import vo.AddJobVO;
import vo.UpdateJobVO;

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
    public List<JobItemDTO> jobList(@RequestParam int studentId, @RequestParam int page, @RequestParam(defaultValue = "") String keyword) {
        return jobService.findJobListById(studentId, page, keyword);
    }

    @RequestMapping("job")
    public String job(Model model, int jobId, HttpSession session) {
        logger.info(jobService.findJobById(jobId).toString());
        model.addAttribute("job", jobService.findJobById(jobId));
        model.addAttribute("jobId", jobId);
        model.addAttribute("filePrefix",fileService.findFilePrefixByJobId(jobId).getFilePrefix());
        return "job";
    }

    @ResponseBody
    @RequestMapping("countJob")
    public int countJob(int studentId, @RequestParam(defaultValue = "") String keyword) {
        return jobService.countJob(studentId, keyword);
    }

    @RequestMapping(value = "addJob", method = RequestMethod.GET)
    public String addJob(Model model) {
        model.addAttribute("courseList",courseService.selectCourseDTOListTch(1));
        return "addJob";
    }
    @ResponseBody
    @RequestMapping(value = "addJob", method = RequestMethod.POST)
    public void addJob(Model model, AddJobVO addJobVO) {
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
        return "updateJob";
    }

    @ResponseBody
    @RequestMapping(value = "updateJob", method = RequestMethod.POST)
    public void updateJob(UpdateJobVO updateJobVO) {
        logger.info(updateJobVO.toString());
        jobService.updateJob(new JobEntity(updateJobVO));
    }

}
