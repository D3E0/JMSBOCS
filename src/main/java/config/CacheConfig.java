package config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableCaching
@EnableScheduling
@ComponentScan(basePackages = "service")
public class CacheConfig {
    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        JedisConnectionFactory cf=new JedisConnectionFactory();
        cf.afterPropertiesSet();
        //默认配置如下localhost 和6379端口
        cf.setHostName("127.0.0.1");
        cf.setPort(6379);
        return cf;
    }
    @Bean
    public RedisTemplate<String, Object>redisTemplate(RedisConnectionFactory connectionFactory){
        RedisTemplate<String, Object> redis=new RedisTemplate<String, Object>();
        redis.setConnectionFactory(connectionFactory);
        redis.afterPropertiesSet();
        return redis;
    }
    @Bean
    public StringRedisTemplate stringRedisTemplate(RedisConnectionFactory connectionFactory){
        return new StringRedisTemplate(connectionFactory);
    }
    @Bean
    public CacheManager cacheManager(RedisTemplate redisTemplate){
        RedisCacheManager cacheManager=new RedisCacheManager(redisTemplate);
        Map<String,Long> time = new HashMap<>();
        time.put("UploadToken",3000L);
        time.put("publicFileList",3000L);
        time.put("allPublicFile",3000L);
        cacheManager.setExpires(time);
        return cacheManager;
    }
}