package dto;

public class UserSDTO {
    private Integer userId;
    private String username;
    private String specialty;

    public UserSDTO() {
    }

    public UserSDTO(Integer userId, String username, String specialty) {
        this.userId = userId;
        this.username = username;
        this.specialty = specialty;
    }

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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        UserSDTO userSDTO = (UserSDTO) o;
        return userId.equals(userSDTO.userId);
    }

    @Override
    public int hashCode() {
        return userId.hashCode();
    }
}
