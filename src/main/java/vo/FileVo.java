package vo;

import com.qiniu.storage.model.FileInfo;

/**
 * @author yan
 * @date 2018/12/1 13:54
 * @descripition
 */
public class FileVo {
    private String fileName;
    private String fileSize;
    private String downloadUrl;
    private long uploadTime;

    public FileVo() {
    }
    public FileVo(FileInfo fileInfo) {
        fileName=fileInfo.key;
        if (fileInfo.fsize<1024*1024){
            fileSize=fileInfo.fsize/1024+"KB";
        }else if (fileInfo.fsize<1024*1024*1024){
            fileSize=(int)fileInfo.fsize/1024/1024+"MB";
        }else if (fileInfo.fsize<1024*1024*1024*1024){
            fileSize=(int)fileInfo.fsize/1024/1024/1024+"GB";
        }
        uploadTime=fileInfo.putTime;
    }
    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileSize() {
        return fileSize;
    }

    public void setFileSize(String fileSize) {
        this.fileSize = fileSize;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String dowloadUrl) {
        this.downloadUrl = dowloadUrl;
    }

    public long getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(long uploadTime) {
        this.uploadTime = uploadTime;
    }
}
