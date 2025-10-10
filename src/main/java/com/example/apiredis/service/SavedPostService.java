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
    private static final String POST_KEY_PREFIX = "post:";
    private static final String USER_KEY_PREFIX = ":user:";

    public SavedPostService(RedisTemplate<String, Object> redisTemplate, PostRepository postRepository) {
        this.redisTemplate = redisTemplate;
        this.postRepository = postRepository;
    }

    private String getRedisSetKey(Long userId) {
        return REDIS_SET_PREFIX + userId;
    }

    public void savePost(Long userId, Long postId) {
        redisTemplate.opsForSet().add(getRedisSetKey(userId), postId.toString());
    }

    public void removePost(Long userId, Long postId) {
        redisTemplate.opsForSet().remove(getRedisSetKey(userId), postId.toString());
    }

    public List<SavedPostResponseDTO> getSavedPosts(Long userId) {
        Set<Object> members = redisTemplate.opsForSet().members(getRedisSetKey(userId));
        if (members == null || members.isEmpty()) return List.of();

        String userIdString = userId.toString();

        // Constrói as chaves Redis completas para a busca
        Set<String> postKeysToFind = members.stream()
                .map(postId -> POST_KEY_PREFIX + postId.toString() + USER_KEY_PREFIX + userIdString)
                .collect(Collectors.toSet());

        Iterable<Post> postsIterable = postRepository.findAllById(postKeysToFind);

        return StreamSupport.stream(postsIterable.spliterator(), false)
                .map(post -> new SavedPostResponseDTO(
                        post.getId_post(),
                        post.getId_user(),
                        post.getAtivo()))
                .collect(Collectors.toList());
    }

    public void clearSavedPosts(Long userId) {
        redisTemplate.delete(getRedisSetKey(userId));
    }
}