package com.example.apiredis.controller;

import com.example.apiredis.dto.NoticiaResponseDTO;
import com.example.apiredis.service.NoticiaService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/noticias")
public class NoticiaController {

    private final NoticiaService noticiaService;

    public NoticiaController(NoticiaService noticiaService) {
        this.noticiaService = noticiaService;
    }

    @GetMapping("/listar")
    public List<NoticiaResponseDTO> getAllNoticias() {
        return noticiaService.getAllNoticias();
    }

    @GetMapping("/listar/{id}")
    public NoticiaResponseDTO getNoticiaById(Long id) {
        return noticiaService.getNoticiaById(id);
    }





}
