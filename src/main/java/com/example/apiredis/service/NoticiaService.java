package com.example.apiredis.service;

import com.example.apiredis.model.Noticia;
import com.example.apiredis.repository.NoticiaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;

    // Injeção de dependência do Repositório
    public NoticiaService(NoticiaRepository noticiaRepository) {
        this.noticiaRepository = noticiaRepository;
    }

    public Noticia salvarNoticia(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }

    // Método de Negócio: Buscar Notícia Individual
    public Noticia buscarPorLink(String link) {
        return noticiaRepository.findByLink(link);
    }

    // Método de Negócio: Listar Todas
    public List<Noticia> listarTodas() {
        return noticiaRepository.findAll();
    }

    // Método de Negócio: Verificar Existência
    public boolean existePorLink(String link) {
        return noticiaRepository.existsByLink(link);
    }

    // Método de Negócio: Remover Notícia
    public boolean removerPorLink(String link) {
        return noticiaRepository.deleteByLink(link);
    }

    // Método de Negócio: Apagar Todas
    public long apagarTodas() {
        return noticiaRepository.deleteAll();
    }
}