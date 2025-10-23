package com.example.apiredis.config;

import com.example.apiredis.model.Noticia;
import com.example.apiredis.model.Post; // Importe a classe Post
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary; // Importe @Primary
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public RedisTemplate<String, Noticia> noticiaRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Noticia> template = new RedisTemplate<>();
        configureTemplate(template, connectionFactory);
        return template;
    }

    @Bean
    public RedisTemplate<String, Post> postRedisTemplate(RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Post> template = new RedisTemplate<>();
        configureTemplate(template, connectionFactory);
        return template;
    }

    private void configureTemplate(RedisTemplate<?, ?> template, RedisConnectionFactory connectionFactory) {
        template.setConnectionFactory(connectionFactory);

        StringRedisSerializer stringSerializer = new StringRedisSerializer();
        template.setKeySerializer(stringSerializer);
        template.setHashKeySerializer(stringSerializer);

        GenericJackson2JsonRedisSerializer jsonSerializer = new GenericJackson2JsonRedisSerializer();
        template.setValueSerializer(jsonSerializer);
        template.setHashValueSerializer(jsonSerializer);

        template.afterPropertiesSet();
    }
}