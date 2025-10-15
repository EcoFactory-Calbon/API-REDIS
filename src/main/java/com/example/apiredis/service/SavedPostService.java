package com.example.apiredis.service;

import com.example.apiredis.dto.SavedPostResponseDTO;
import com.example.apiredis.model.Post;
import com.example.apiredis.repository.PostRepository;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SavedPostService {

    private final RedisTemplate<String, Object> redisTemplate;
    private final PostRepository postRepository;
    private static final String REDIS_SET_PREFIX = "saved_posts:";

    public SavedPostService(RedisTemplate<String, Object> redisTemplate, PostRepository postRepository) {
        this.redisTemplate = redisTemplate;
        this.postRepository = postRepository;
    }

    private String getRedisSetKey(Long userId) {
        return REDIS_SET_PREFIX + userId;
    }

    public void savePost(Long userId, String postKey) {
        redisTemplate.opsForSet().add(getRedisSetKey(userId), postKey);
    }

    public void removePost(Long userId, String postKey) {
        redisTemplate.opsForSet().remove(getRedisSetKey(userId), postKey);
    }

    public List<SavedPostResponseDTO> getSavedPosts(Long userId) {
        Set<Object> members = redisTemplate.opsForSet().members(getRedisSetKey(userId));
        if (members == null || members.isEmpty()) return List.of();

        Iterable<Post> posts = postRepository.findAllById(
                members.stream().map(Object::toString).collect(Collectors.toSet())
        );

        return StreamSupport.stream(posts.spliterator(), false)
                .map(p -> new SavedPostResponseDTO(p.getNoticia().getId(), p.getId_user(), p.getAtivo()))
                .collect(Collectors.toList());
    }

    public void clearSavedPosts(Long userId) {
        redisTemplate.delete(getRedisSetKey(userId));
    }
}
