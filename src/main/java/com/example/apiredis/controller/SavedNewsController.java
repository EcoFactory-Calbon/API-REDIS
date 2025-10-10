package com.example.apiredis.controller;

import com.example.apiredis.service.SavedNewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users/{userId}/savedNews")
public class SavedNewsController {

    @Autowired
    private SavedNewsService savedNewsService;

    @PostMapping
    public ResponseEntity<String> salvarNoticia(
            @PathVariable Long userId,
            @RequestParam String newsId
    ) {
        savedNewsService.salvarNoticia(userId, newsId);
        return ResponseEntity.ok("Notícia salva com sucesso!");
    }

    @DeleteMapping("/{newsId}")
    public ResponseEntity<String> removerNoticia(
            @PathVariable Long userId,
            @PathVariable String newsId
    ) {
        savedNewsService.removerNoticia(userId, newsId);
        return ResponseEntity.ok("Notícia removida com sucesso!");
    }

    @GetMapping
    public ResponseEntity<Set<Object>> listarNoticiasSalvas(@PathVariable Long userId) {
        return ResponseEntity.ok(savedNewsService.listarIdsNoticiasSalvas(userId));
    }

    @GetMapping("/count")
    public ResponseEntity<Long> contarNoticiasSalvas(@PathVariable Long userId) {
        return ResponseEntity.ok(savedNewsService.contarNoticiasSalvas(userId));
    }

    @DeleteMapping
    public ResponseEntity<String> apagarTodasNoticias(@PathVariable Long userId) {
        savedNewsService.apagarTodasNoticias(userId);
        return ResponseEntity.ok("Todas as notícias salvas foram apagadas!");
    }

    // usar esse metodo para o android (retorna titulo, descricao e link)
    @GetMapping("/details")
    public ResponseEntity<List<Map<String, Object>>> listarNoticiasDetalhadas(@PathVariable Long userId) {
        List<Map<String, Object>> noticias = savedNewsService.listarNoticiasDetalhadas(userId);
        return ResponseEntity.ok(noticias);
    }
}
