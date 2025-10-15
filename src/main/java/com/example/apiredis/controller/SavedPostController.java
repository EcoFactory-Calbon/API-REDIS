package com.example.apiredis.controller;

import com.example.apiredis.dto.SavedPostResponseDTO;
import com.example.apiredis.dto.SavePostRequestDTO;
import com.example.apiredis.service.SavedPostService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;

@RestController
@RequestMapping("/users/{userId}/savedPosts")
public class SavedPostController {

    private final SavedPostService savedPostService;

    public SavedPostController(SavedPostService savedPostService) {
        this.savedPostService = savedPostService;
    }

    @PostMapping
    public ResponseEntity<String> savePost(@PathVariable Long userId, @RequestBody SavePostRequestDTO requestDTO) {
        if (!userId.equals(requestDTO.getUserId())) {
            return new ResponseEntity<>("O ID do usuário na URL não corresponde ao ID no corpo da requisição.", HttpStatus.BAD_REQUEST);
        }
        // Chave completa do post
        String postKey = "post:" + requestDTO.getPostId() + ":user:" + userId;
        savedPostService.savePost(userId, postKey);
        return ResponseEntity.ok("Post salvo com sucesso!");
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<String> removePost(@PathVariable Long userId, @PathVariable String postId) {
        String postKey = "post:" + postId + ":user:" + userId;
        savedPostService.removePost(userId, postKey);
        return ResponseEntity.ok("Post removido com sucesso!");
    }

    @GetMapping
    public ResponseEntity<List<SavedPostResponseDTO>> listSavedPosts(@PathVariable Long userId) {
        return ResponseEntity.ok(savedPostService.getSavedPosts(userId));
    }

    @DeleteMapping
    public ResponseEntity<String> clearAllSavedPosts(@PathVariable Long userId) {
        savedPostService.clearSavedPosts(userId);
        return ResponseEntity.ok("Todos os posts salvos foram removidos!");
    }
}
