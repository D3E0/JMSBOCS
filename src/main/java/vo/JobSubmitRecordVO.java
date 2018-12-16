package vo;

import dto.JobSubmitRecordDTO;

import java.io.Serializable;
import java.util.List;

public class JobSubmitRecordVO implements Serializable {
    private int code;
    private String msg;
    private int count;
    private List<JobSubmitRecordDTO> data;

    public JobSubmitRecordVO() {
    }

    public JobSubmitRecordVO(List<JobSubmitRecordDTO> data, int code, String msg, int count) {
        this.data = data;
        this.code = code;
        this.msg = msg;
        this.count = count;
    }

    public List<JobSubmitRecordDTO> getData() {
        return data;
    }

    public void setData(List<JobSubmitRecordDTO> data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
