package com.example.apiredis.controller;

import com.example.apiredis.model.Post;
import com.example.apiredis.service.PostService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        if (post.getId_post() == null || post.getId_user() == null) {
            return new ResponseEntity("Campos 'id_post' e 'id_user' são obrigatórios.", HttpStatus.BAD_REQUEST);
        }

        Post savedPost = postService.save(post);
        return new ResponseEntity<>(savedPost, HttpStatus.CREATED); // 201 Created
    }

    @GetMapping("findById/{id}")
    public ResponseEntity<Post> getPostById(@PathVariable String id) {
        return postService.findById(id)
                .map(ResponseEntity::ok) // 200 OK
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        List<Post> posts = postService.findAll();
        return ResponseEntity.ok(posts); // 200 OK
    }

    @PutMapping("updatePostStatus/{id}")
    public ResponseEntity<Post> updatePostStatus(@PathVariable String id, @RequestBody Post postDetails) {
        return postService.updateStatus(id, postDetails)
                .map(ResponseEntity::ok) // 200 OK
                .orElse(ResponseEntity.notFound().build()); // 404 Not Found
    }

    @DeleteMapping("deletePostById/{id}")
    public ResponseEntity<Void> deletePost(@PathVariable String id) {
        if (postService.deleteById(id)) {
            return ResponseEntity.noContent().build(); // 204 No Content
        }
        return ResponseEntity.notFound().build(); // 404 Not Found
    }
}