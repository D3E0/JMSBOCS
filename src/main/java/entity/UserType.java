package entity;

public enum UserType {
    /**
     *
     */
    STUDENT(0),
    /**
     *
     */
    TEACHER(1),
    /**
     *
     */
    ASSISTANT(2),
    /**
     *
     */
    ADMIN(3);
    private int code;

    UserType(int code) {
        this.code = code;
    }
}
