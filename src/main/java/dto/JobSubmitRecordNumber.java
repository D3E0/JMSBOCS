package dto;

import java.io.Serializable;

/**
 * @author yan
 * @date 2018/12/10 13:01
 * @descripition
 */
public class JobSubmitRecordNumber implements Serializable {
    private int need;
    private int already;

    public int getNeed() {
        return need;
    }

    public void setNeed(int need) {
        this.need = need;
    }

    public int getAlready() {
        return already;
    }

    public void setAlready(int already) {
        this.already = already;
    }
}
