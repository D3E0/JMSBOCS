package entity;

import java.io.Serializable;

public class QiniuEntity implements Serializable {

    private Integer qiniuId;

    private String sk;

    private String ak;

    private String bucket;

    public QiniuEntity() {
    }

    public QiniuEntity(String sk, String ak, String bucket) {
        this.sk = sk;
        this.ak = ak;
        this.bucket = bucket;
    }

    public Integer getQiniuId() {
        return qiniuId;
    }

    public void setQiniuId(Integer qiniuId) {
        this.qiniuId = qiniuId;
    }

    public String getSk() {
        return sk;
    }

    public void setSk(String sk) {
        this.sk = sk == null ? null : sk.trim();
    }

    public String getAk() {
        return ak;
    }

    public void setAk(String ak) {
        this.ak = ak == null ? null : ak.trim();
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket == null ? null : bucket.trim();
    }
}