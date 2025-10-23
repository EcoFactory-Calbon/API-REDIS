package com.example.apiredis.controller;

import com.example.apiredis.model.Noticia;
import com.example.apiredis.service.NoticiaService;
import openapi.NoticiaOpenApi;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/noticias")
public class NoticiaController implements NoticiaOpenApi {

    private final NoticiaService service;

    public NoticiaController(NoticiaService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Noticia saveNoticia(@RequestBody Noticia noticia) {
        return service.salvarNoticia(noticia);
    }

    @GetMapping
    public ResponseEntity<Noticia> getNoticiaByLink(@RequestParam String link) {
        Noticia noticia = service.buscarPorLink(link);

        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/listar")
    public List<Noticia> listAllNoticias() {
        return service.listarTodas();
    }

    @GetMapping("/existe")
    public ResponseEntity<Map<String, Boolean>> checkExistence(@RequestParam String link) {
        boolean exists = service.existePorLink(link);

        return ResponseEntity.ok(Map.of("salva", exists));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteNoticiaByLink(@RequestParam String link) {
        boolean wasDeleted = service.removerPorLink(link);

        if (wasDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    @DeleteMapping("/todas")
    public ResponseEntity<Map<String, String>> deleteAllNoticias() {
        long count = service.apagarTodas();

        String message = String.format("%d notícias foram removidas do Redis.", count);
        return ResponseEntity.ok(Map.of("status", "sucesso", "mensagem", message));
    }
}