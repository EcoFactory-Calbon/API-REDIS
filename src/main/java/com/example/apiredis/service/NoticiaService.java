package com.example.apiredis.service;

import com.example.apiredis.dto.NoticiaRequestDTO;
import com.example.apiredis.dto.NoticiaResponseDTO;
import com.example.apiredis.model.Noticia;
import com.example.apiredis.repository.NoticiaRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;

    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    public List<NoticiaResponseDTO> getAllNoticias() {
        return StreamSupport.stream(noticiaRepository.findAll().spliterator(), false)
                .map(NoticiaResponseDTO::new)
                .collect(Collectors.toList());
    }

    public NoticiaResponseDTO getNoticiaById(Long id) {
        return noticiaRepository.findById(id)
                .map(NoticiaResponseDTO::new)
                .orElse(null);
    }

    public NoticiaResponseDTO saveNoticia(NoticiaRequestDTO noticiaRequestDTO) {
        Noticia noticia = new Noticia();
        noticia.setTitulo(noticiaRequestDTO.getTitulo());
        noticia.setDescricao(noticiaRequestDTO.getDescricao());
        noticia.setLink(noticiaRequestDTO.getLink());
        noticia.setData(new Date());
        noticia.setIdHash(noticiaRequestDTO.getIdHash());

        Noticia savedNoticia = noticiaRepository.save(noticia);
        return new NoticiaResponseDTO(savedNoticia);
    }

    public void deleteNoticia(Long id) {
        noticiaRepository.deleteById(id);
    }
}