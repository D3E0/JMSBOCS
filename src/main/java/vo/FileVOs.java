package vo;

import java.io.Serializable;
import java.util.List;

/**
 * @author yan
 * @date 2018/12/8 15:18
 * @descripition
 */
public class FileVOs implements Serializable {
    private int count;
    private List<FileVO> fileVOList;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<FileVO> getFileVOList() {
        return fileVOList;
    }

    public void setFileVOList(List<FileVO> fileVOList) {
        this.fileVOList = fileVOList;
    }
}
