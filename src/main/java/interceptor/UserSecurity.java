package interceptor;

import entity.UserType;
import org.springframework.core.NamedThreadLocal;
import org.springframework.util.StringUtils;

public class UserSecurity {
    private static class UserInfo {
        String name;
        Integer id;
        UserType type;
    }

    private static final ThreadLocal<UserInfo> USER_INFO_THREAD_LOCAL = new NamedThreadLocal<UserInfo>("Jmsbocs-id");

    public static String getName() {
        UserInfo userInfo = USER_INFO_THREAD_LOCAL.get();
        return (userInfo != null ? userInfo.name : null);
    }

    public static Integer getId() {
        UserInfo userInfo = USER_INFO_THREAD_LOCAL.get();
        return (userInfo != null ? userInfo.id : null);
    }

    public UserType gteType() {
        UserInfo userInfo = USER_INFO_THREAD_LOCAL.get();
        return (userInfo != null ? userInfo.type : null);
    }

    public static void setUser(String name, Integer id, UserType type) {
        UserInfo userInfo = new UserInfo();
        userInfo.name = name;
        userInfo.id = id;
        userInfo.type = type;
        USER_INFO_THREAD_LOCAL.set(userInfo);
    }

    public static boolean isUserSignedIn(String name) {
        UserInfo userInfo = USER_INFO_THREAD_LOCAL.get();
        return userInfo != null && userInfo.name.equals(name);
    }

    public static boolean isSignedIn() {
        return StringUtils.hasText(getName());
    }

    public static void clean() {
        USER_INFO_THREAD_LOCAL.remove();
    }

}
