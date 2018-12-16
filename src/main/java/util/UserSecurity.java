package util;

import dto.UserInfo;
import entity.UserType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StringUtils;

public class UserSecurity {


    private static final Logger logger = LogManager.getLogger(UserSecurity.class);

    private static final ThreadLocal<UserInfo> USER_INFO_THREAD_LOCAL = new NamedThreadLocal<UserInfo>("Jmsbocs-id");

    public static String getName() {
        UserInfo userInfo = USER_INFO_THREAD_LOCAL.get();
        return (userInfo != null ? userInfo.getName() : null);
    }

    public static Integer getId() {
        UserInfo userInfo = USER_INFO_THREAD_LOCAL.get();
        return (userInfo != null ? userInfo.getId() : null);
    }

    public UserType getType() {
        UserInfo userInfo = USER_INFO_THREAD_LOCAL.get();
        return (userInfo != null ? userInfo.getType() : null);
    }

    public static UserInfo getUser() {
        return USER_INFO_THREAD_LOCAL.get();
    }

    public static void setUser(Integer userId, String username, UserType type) {
        UserInfo userInfo = new UserInfo();
        userInfo.setName(username);
        userInfo.setId(userId);
        userInfo.setType(type);
//        logger.info(String.format("set user %s to thread local", userInfo));
        USER_INFO_THREAD_LOCAL.set(userInfo);
    }

    public static boolean isTeacher() {
        UserInfo userInfo = USER_INFO_THREAD_LOCAL.get();
        return userInfo != null && userInfo.getType().equals(UserType.TEACHER);
    }

    public static boolean isUserSignedIn(String name) {
        UserInfo userInfo = USER_INFO_THREAD_LOCAL.get();
        return userInfo != null && userInfo.getName().equals(name);
    }

    public static boolean isSignedIn() {
        return StringUtils.hasText(getName());
    }

    public static void clean() {
        USER_INFO_THREAD_LOCAL.remove();
    }

}
