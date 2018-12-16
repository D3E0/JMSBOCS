package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FileService;
import service.JobService;
import vo.FileVO;
import vo.FileVOs;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/5 20:59
 * @descripition
 */
@Controller
public class FileController {
    private FileService fileService;
    private JobService jobService;
    @Autowired
    public void setJobService(JobService jobService) {
        this.jobService = jobService;
    }

    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
    @ResponseBody
    @RequestMapping(value = "qiniu")
    public String getQiniu(int courseId){
        return fileService.getUploadToken(courseId);
    }
    @ResponseBody
    @RequestMapping(value = "uploadFiles")
    public List<FileVO> getUploadFiles(int courseId, int jobId, int studentId){
        return fileService.getFileList(courseId,jobId,studentId);
    }
    @ResponseBody
    @RequestMapping(value = "getAllFiles")
    public List<FileVO> getAllFiles(int jobId){
        int courseId=jobService.findJobById(jobId).getCourseId();
        return fileService.getAllFile(courseId,jobId);
    }

    @ResponseBody
    @RequestMapping(value = "publicFiles")
    public FileVOs getPublicFiles(int courseId, @RequestParam(defaultValue = "1")int page){
        return fileService.getPublicFiles(courseId,page);
    }
    @ResponseBody
    @RequestMapping(value = "deleteFile")
    public int deleFile(int courseId, String key,int jobId){
        return fileService.deleteFile(courseId,key,jobId);
    }
    @RequestMapping(value = "jobFileList", method = RequestMethod.GET)
    public String jobFileList(Model model,int jobId,int studentId) {
        int courseId=jobService.findJobById(jobId).getCourseId();
        model.addAttribute("jobId",jobId);
        model.addAttribute("courseId",courseId);
        model.addAttribute("jobFileList",fileService.getFileList(courseId,jobId,studentId));
        return "jobFileList";
    }

    @RequestMapping(value = "downloadAll", method = RequestMethod.GET)
    public String downloadAll(Model model,int jobId) {
        model.addAttribute("filePrefix",fileService.findFilePrefixByJobId(jobId).getFilePrefix());
        model.addAttribute("jobId", jobId);
        return "fileProgress";
    }
    @ResponseBody
    @RequestMapping(value = "getPublicUrl")
    public String getPublicUrl(String remoteSrcUrl, String key){
        return "https://view.officeapps.live.com/op/view.aspx?src="+fileService.getPublicUrl(remoteSrcUrl,key);
    }
}
