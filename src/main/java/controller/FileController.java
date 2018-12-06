package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FileService;
import vo.FileVo;

import java.util.List;

/**
 * @author yan
 * @date 2018/12/5 20:59
 * @descripition
 */
@Controller
public class FileController {
    private FileService fileService;
    @Autowired
    public void setFileService(FileService fileService) {
        this.fileService = fileService;
    }
    @ResponseBody
    @RequestMapping(value = "qiniu")
    public String getqiniu(int courseId){
        return fileService.getUploadToken(courseId);
    }
    @ResponseBody
    @RequestMapping(value = "uploadfiles")
    public List<FileVo> getuploadfiles(int courseId, int jobId, int studentId){
        return fileService.getFileList(courseId,jobId,studentId);
    }
    @ResponseBody
    @RequestMapping(value = "delefile")
    public int delefile(int courseId, String key){
        return fileService.delefile(courseId,key);
    }
    @RequestMapping(value = "jobfilelist", method = RequestMethod.GET)
    public String jobfilelist() {
        return "jobfilelist";
    }
}
