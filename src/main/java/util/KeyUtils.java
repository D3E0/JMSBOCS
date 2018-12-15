package util;

public class KeyUtils {
    private static final String UID = "uid:";

    /**
     * type: string
     * mode: uid:[uid]:auth --> auth
     *
     * @param uid
     * @return
     */
    public static String auth(Integer uid) {
        return UID + uid + ":auth";
    }

    /**
     * type: string
     * mode: auth:[auth] --> uid
     *
     * @param auth
     * @return
     */
    public static String authKey(String auth) {
        return "auth:" + auth;
    }

    /**
     * type: hash
     * mode: uid:[uid] --> userInfo
     *
     * @param uid
     * @return
     */
    public static String uid(String uid) {
        return UID + uid;
    }

}
