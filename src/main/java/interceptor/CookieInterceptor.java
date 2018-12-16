package interceptor;

import dto.UserInfo;
import manager.AuthRepository;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import service.UserService;
import util.UserSecurity;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ACM-PC
 */
@Component
public class CookieInterceptor extends HandlerInterceptorAdapter {

    private final AuthRepository repository;
    private final UserService userService;
    private static final Logger logger = LogManager.getLogger(CookieInterceptor.class);

    public static final String JMSBOCS_COOKIE = "jmsbocsauth";
    public final static String[] PATH = {
            "/home", "/user", "/subject", "/course", "/api/*",
            "/job","/jobSubmitRecord","/jobList","/addJob","/deleteJob","/updateJob","/jobSubmitRecordNum","/jobItemSubmit","/countJob","getJobContent",
            "/qiniu","/uploadFiles","/getAllFiles","/deleteFile","/jobFileList","/downloadAll"

    };

    @Autowired
    public CookieInterceptor(AuthRepository repository, UserService userService) {
        this.repository = repository;
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String uri = request.getRequestURI();
        logger.info("Request URI: " + uri);

        Cookie[] cookies = request.getCookies();

        if (!ObjectUtils.isEmpty(cookies)) {
            for (Cookie cookie : cookies) {
                if (JMSBOCS_COOKIE.equals(cookie.getName())) {
                    String auth = cookie.getValue();
                    Integer uid = repository.getUid(auth);
                    logger.info(String.format("auth %s ==> %s", auth, uid));
                    if (uid != null) {
//                        TODO 缓存支持
                        UserInfo userInfo = userService.selectUserInfo(uid);
                        if (userInfo != null) {
                            logger.info(String.format("uid %s ==> %s", uid, userInfo));
                            UserSecurity.setUser(userInfo.getId(), userInfo.getName(), userInfo.getType());
                            return true;
                        }
                    }
                }
            }
        }
        if (uri.contains("api/")) {
            request.getRequestDispatcher("/error/api").forward(request, response);
        } else {
            response.sendRedirect(request.getContextPath() + "/login");
        }
        return false;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        UserSecurity.clean();
    }
}
