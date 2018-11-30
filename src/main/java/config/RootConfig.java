package config;

import manager.DeafultHandler;
import manager.NotifyManager;
import mapper.NotifyMapper;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

@Configuration
@MapperScan(basePackages = "mapper")
@ComponentScan(basePackages = {"mapper", "service", "manager", "controller"},
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ANNOTATION, value = EnableWebMvc.class)
        })
public class RootConfig {
    private static final Logger logger = LogManager.getLogger(RootConfig.class);

    @Bean
    public DataSource dataSource() throws IOException {
        DriverManagerDataSource ds = new DriverManagerDataSource();
        Properties properties = new Properties();
        Resource resource = new ClassPathResource("config.properties");
        properties.load(resource.getInputStream());
        ds.setDriverClassName(properties.getProperty("database.driver"));
        ds.setPassword(properties.getProperty("database.password"));
        ds.setUsername(properties.getProperty("database.username"));
        ds.setUrl(properties.getProperty("database.url"));
        logger.info("----- DataSource Init Success -----");
        return ds;
    }

    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setConfigLocation(new ClassPathResource("mybatis-config.xml"));
        logger.info("----- SqlSessionFactory Init Success -----");
        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public NotifyManager notifyManager(NotifyMapper notifyMapper, DeafultHandler deafultHandler) {
        NotifyManager notifyManager = new NotifyManager(notifyMapper, deafultHandler);
        return notifyManager;
    }
}

