package dto;

import entity.UserType;

public class UserDTO {
    private Integer userId;

    private String username;

    private String telephone;

    private String email;

    private String specialty;

    private String office;

    private Integer qiniuId;

    private String bucket;

    private UserType type;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public Integer getQiniuId() {
        return qiniuId;
    }

    public void setQiniuId(Integer qiniuId) {
        this.qiniuId = qiniuId;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public UserType getType() {
        return type;
    }

    public void setType(UserType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "userId=" + userId +
                ", username='" + username + '\'' +
                ", telephone='" + telephone + '\'' +
                ", email='" + email + '\'' +
                ", specialty='" + specialty + '\'' +
                ", office='" + office + '\'' +
                ", qiniuId=" + qiniuId +
                ", bucket='" + bucket + '\'' +
                ", type=" + type +
                '}';
    }
}
