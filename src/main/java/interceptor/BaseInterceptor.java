package interceptor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author ACM-PC
 */
public class BaseInterceptor extends HandlerInterceptorAdapter {
    private static final Logger logger = LogManager.getLogger(BaseInterceptor.class);

    public static final String TOKEN_KEY = "x-token";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        response.setHeader("Access-Control-Allow-Origin", "*");
        response.setHeader("Access-Control-Allow-Methods", "GET, POST");
        response.setHeader("Access-Control-Allow-Headers", TOKEN_KEY);
//        String uri = request.getRequestURI();
//        logger.info("Request URI: " + uri);
        return true;
    }

}

// if (!response.containsHeader("Access-Control-Allow-Origin")) {
//         response.addHeader("Access-Control-Allow-Origin", "*");
//         }
//
//         if (!response.containsHeader("Access-Control-Allow-Methods")) {
//         response.addHeader("Access-Control-Allow-Methods", "GET, POST");
//         }
//
//         if (!response.containsHeader("Access-Control-Allow-Headers")) {
//         response.addHeader("Access-Control-Allow-Headers", TOKEN_KEY);
//         }
