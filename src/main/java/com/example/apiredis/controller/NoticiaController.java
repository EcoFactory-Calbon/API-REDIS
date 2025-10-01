package com.example.apiredis.controller;

import com.example.apiredis.dto.NoticiaRequestDTO;
import com.example.apiredis.dto.NoticiaResponseDTO;
import com.example.apiredis.model.Noticia;
import com.example.apiredis.service.NoticiaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/noticias")
public class NoticiaController {

    private final NoticiaService noticiaService;

    @Autowired
    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @PostMapping
    public ResponseEntity<NoticiaResponseDTO> criarNoticia(@Valid @RequestBody NoticiaRequestDTO requestDTO) {

        Noticia noticia = new Noticia(
                requestDTO.getLink(),
                requestDTO.getNome(),
                requestDTO.getData(),
                requestDTO.getDescricao()
        );

        Noticia noticiaSalva = noticiaService.salvarOuAtualizar(noticia);

        return new ResponseEntity<>(new NoticiaResponseDTO(noticiaSalva), HttpStatus.CREATED);
    }

    @GetMapping("/{link}")
    public ResponseEntity<NoticiaResponseDTO> buscarNoticia(@PathVariable String link) {
        // O Service lança 404 (ResourceNotFoundException) se não encontrar.
        Noticia noticia = noticiaService.buscarPorLink(link);

        // Busca o tempo de vida restante
        Long ttl = noticiaService.getNoticiaTTL(link);

        // Retorna a notícia com o TTL
        NoticiaResponseDTO responseDTO = new NoticiaResponseDTO(noticia, ttl);
        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping
    public ResponseEntity<List<NoticiaResponseDTO>> listarTodasNoticias() {
        Iterable<Noticia> noticias = noticiaService.buscarTodas();

        // Converte para uma lista de DTOs, buscando o TTL para cada item
        List<NoticiaResponseDTO> responseList = StreamSupport.stream(noticias.spliterator(), false)
                .map(noticia -> {
                    Long ttl = noticiaService.getNoticiaTTL(noticia.getLink());
                    return new NoticiaResponseDTO(noticia, ttl);
                })
                .collect(Collectors.toList());

        return ResponseEntity.ok(responseList);
    }

    @DeleteMapping("/{link}")
    public ResponseEntity<Void> deletarNoticia(@PathVariable String link) {
        noticiaService.deletarPorLink(link);
        return ResponseEntity.noContent().build();
    }
}