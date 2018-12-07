package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import service.FileService;
import vo.FileVO;

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
    public String getQiniu(int courseId){
        return fileService.getUploadToken(courseId);
    }
    @ResponseBody
    @RequestMapping(value = "uploadFiles")
    public List<FileVO> getUploadFiles(int courseId, int jobId, int studentId){
        return fileService.getFileList(courseId,jobId,studentId);
    }
    @ResponseBody
    @RequestMapping(value = "publicFiles")
    public List<FileVO> getPublicFiles(int courseId){
        return fileService.getPublicFileList(courseId);
    }
    @ResponseBody
    @RequestMapping(value = "publicFilesCount")
    public int getPublicFilesCount(int courseId){
        return fileService.getPublicFileList(courseId).size();
    }
    @ResponseBody
    @RequestMapping(value = "deleteFile")
    public int deleFile(int courseId, String key){
        return fileService.delefile(courseId,key);
    }
    @RequestMapping(value = "jobFileList", method = RequestMethod.GET)
    public String jobFileList() {
        return "jobFileList";
    }
}
