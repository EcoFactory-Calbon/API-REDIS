package com.example.apiredis.service;

import com.example.apiredis.model.Post;
import com.example.apiredis.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public Post save(Post post) {
        if (post.getNoticia() == null || post.getNoticia().getId() == null || post.getId_user() == null) {
            throw new IllegalArgumentException("Campos 'noticia.id' e 'id_user' são obrigatórios.");
        }
        post.gerarChaveRedis();
        return postRepository.save(post);
    }

    public Optional<Post> findById(String id) {
        return postRepository.findById(id);
    }

    public List<Post> findAll() {
        return StreamSupport.stream(postRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }

    public Optional<Post> updateStatus(String id, Post postDetails) {
        return postRepository.findById(id)
                .map(existingPost -> {
                    existingPost.setAtivo(postDetails.getAtivo());
                    return postRepository.save(existingPost);
                });
    }

    public boolean deleteById(String id) {
        if (postRepository.existsById(id)) {
            postRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
