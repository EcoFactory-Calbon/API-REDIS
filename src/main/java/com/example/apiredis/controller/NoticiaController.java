package com.example.apiredis.controller;

import com.example.apiredis.model.Noticia;
import com.example.apiredis.service.NoticiaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    // O Controller agora depende do Service
    private final NoticiaService service;

    public NoticiaController(NoticiaService service) {
        this.service = service;
    }

    // Endpoint: Salvar Notícia
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Noticia saveNoticia(@RequestBody Noticia noticia) {
        return service.salvarNoticia(noticia);
    }

    // Endpoint: Buscar Notícia Individual (por link)
    @GetMapping
    public ResponseEntity<Noticia> getNoticiaByLink(@RequestParam String link) {
        Noticia noticia = service.buscarPorLink(link);

        if (noticia != null) {
            return ResponseEntity.ok(noticia);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Endpoint: Listar Todas as Notícias Salvas
    @GetMapping("/listar")
    public List<Noticia> listAllNoticias() {
        return service.listarTodas();
    }

    // Endpoint: Verificar se a Notícia está Salva
    @GetMapping("/existe")
    public ResponseEntity<Map<String, Boolean>> checkExistence(@RequestParam String link) {
        boolean exists = service.existePorLink(link);

        return ResponseEntity.ok(Map.of("salva", exists));
    }

    // Endpoint: Remover Notícia Individual (por link)
    @DeleteMapping
    public ResponseEntity<Void> deleteNoticiaByLink(@RequestParam String link) {
        boolean wasDeleted = service.removerPorLink(link);

        if (wasDeleted) {
            return ResponseEntity.noContent().build(); // 204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 404 Not Found
        }
    }

    // Endpoint: Apagar Todas as Notícias Salvas
    @DeleteMapping("/todas")
    public ResponseEntity<Map<String, String>> deleteAllNoticias() {
        long count = service.apagarTodas();

        String message = String.format("%d notícias foram removidas do Redis.", count);
        return ResponseEntity.ok(Map.of("status", "sucesso", "mensagem", message));
    }
}