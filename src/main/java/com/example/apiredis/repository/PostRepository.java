package com.example.apiredis.repository;

import com.example.apiredis.model.Post;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
public class PostRepository {

    private final HashOperations<String, String, Post> hashOperations;
    private final RedisTemplate<String, Post> redisTemplate;
    private static final String POST_HASH_FIELD = "details";
    public static final String KEY_PREFIX = "post:";

    public PostRepository(RedisTemplate<String, Post> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    private String generateRedisKey(String userId, String postId) {
        return KEY_PREFIX + "user:" + userId + ":post:" + postId;
    }


    public Post save(Post post) {
        String key = generateRedisKey(post.getId_user(), post.getId_post());
        hashOperations.put(key, POST_HASH_FIELD, post);
        return post;
    }

    public Post findByIds(String userId, String postId) {
        String key = generateRedisKey(userId, postId);
        return hashOperations.get(key, POST_HASH_FIELD);
    }

    public boolean existsByIds(String userId, String postId) {
        String key = generateRedisKey(userId, postId);
        return redisTemplate.hasKey(key);
    }

    public boolean deleteByIds(String userId, String postId) {
        String key = generateRedisKey(userId, postId);
        Boolean result = redisTemplate.delete(key);
        return result != null && result;
    }

    public List<Post> findAll() {
        List<Post> posts = new ArrayList<>();
        ScanOptions options = ScanOptions.scanOptions().match(KEY_PREFIX + "*").count(500).build();
        try (Cursor<String> cursor = redisTemplate.scan(options)) {
            while (cursor.hasNext()) {
                String key = cursor.next();
                if (redisTemplate.type(key) == DataType.HASH) {
                    Post post = hashOperations.get(key, POST_HASH_FIELD);
                    if (post != null) {
                        posts.add(post);
                    }
                }
            }
        }
        return posts;
    }

    public long deleteAll() {
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");
        if (keys != null && !keys.isEmpty()) {
            return redisTemplate.delete(keys);
        }
        return 0;
    }
}