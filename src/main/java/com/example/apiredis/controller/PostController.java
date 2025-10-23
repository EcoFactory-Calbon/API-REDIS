package com.example.apiredis.controller;

import com.example.apiredis.model.Post;
import com.example.apiredis.service.PostService;
import openapi.PostOpenApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/posts")
public class PostController implements PostOpenApi {

    private final PostService service;

    public PostController(PostService service) {
        this.service = service;
    }

    @PostMapping("/salvar")
    @ResponseStatus(HttpStatus.CREATED)
    public Post savePost(@RequestBody Post post) {
        return service.salvarPost(post);
    }



    @GetMapping("/existe")
    public ResponseEntity<Map<String, Boolean>> checkExistence(@RequestParam String userId, @RequestParam String postId) {
        boolean exists = service.existePorIds(userId, postId);
        return ResponseEntity.ok(Map.of("salvo", exists));
    }

    @DeleteMapping("/excluir")
    public ResponseEntity<Void> deletePostByIds(@RequestParam String userId, @RequestParam String postId) {
        boolean wasDeleted = service.removerPorIds(userId, postId);
        if (wasDeleted) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/todos")
    public ResponseEntity<Map<String, String>> deleteAllPosts() {
        long count = service.apagarTodos();
        String message = String.format("%d posts foram removidos do Redis.", count);
        return ResponseEntity.ok(Map.of("status", "sucesso", "mensagem", message));
    }
}