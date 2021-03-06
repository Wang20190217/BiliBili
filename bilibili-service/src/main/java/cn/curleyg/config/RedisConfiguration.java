package cn.curleyg.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;

/**
 * 城南花已开<br>
 *
 * @Description: Redis配置类<br>
 * @Project: <br>
 * @CreateDate: Created in 2022/4/30 00:11 <br>
 * @Author: Wang
 */
@Configuration
public class RedisConfiguration {


    // 自定义一个方法返回RedisTemplate
    @Bean
    public RedisTemplate<String, Object> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(factory);
        // key采用String序列化方式
        template.setKeySerializer(RedisSerializer.string());
        // hash的key采用String序列化方式
        template.setHashKeySerializer(RedisSerializer.string());
        // value采用jackson序列化方式
        template.setValueSerializer(RedisSerializer.json());
        // hash的value采用jackson序列化方式
        template.setHashValueSerializer(RedisSerializer.json());

        return template;
    }


}