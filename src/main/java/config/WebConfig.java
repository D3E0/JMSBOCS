package config;

import interceptor.BaseInterceptor;
import interceptor.CookieInterceptor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * @author ACM-PC
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = {"controller", "interceptor"})
public class WebConfig implements WebMvcConfigurer {
    private static final Logger logger = LogManager.getLogger(WebConfig.class);
    private CookieInterceptor cookieInterceptor;

    @Autowired
    public void setCookieInterceptor(CookieInterceptor cookieInterceptor) {
        this.cookieInterceptor = cookieInterceptor;
    }

    /**
     * 配置拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new BaseInterceptor());
        registry.addInterceptor(cookieInterceptor).addPathPatterns(CookieInterceptor.PATH).excludePathPatterns("/error/**");
    }

    /**
     * 配置视图解析器
     */
    @Bean
    public ViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/views/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        configurer.enable();
    }
}
