package com.example.apiredis.service;

import com.example.apiredis.model.Post;
import com.example.apiredis.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post salvarPost(Post post) {
        return postRepository.save(post);
    }

    public Post buscarPorIds(String userId, String postId) {
        return postRepository.findByIds(userId, postId);
    }

    public List<Post> listarTodos() {
        return postRepository.findAll();
    }

    public boolean existePorIds(String userId, String postId) {
        return postRepository.existsByIds(userId, postId);
    }

    public boolean removerPorIds(String userId, String postId) {
        return postRepository.deleteByIds(userId, postId);
    }

    public long apagarTodos() {
        return postRepository.deleteAll();
    }
}