package com.example.apiredis.service;

import com.example.apiredis.model.Noticia;
import com.example.apiredis.repository.NoticiaRepository;
import com.example.apiredis.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class NoticiaService {

    private final NoticiaRepository noticiaRepository;
    private final RedisTemplate<String, Object> redisTemplate; // Para obter o TTL

    // Injeção de dependência via construtor
    @Autowired
    public NoticiaService(NoticiaRepository noticiaRepository, RedisTemplate<String, Object> redisTemplate) {
        this.noticiaRepository = noticiaRepository;
        this.redisTemplate = redisTemplate;
    }

    public Noticia salvarOuAtualizar(Noticia noticia) {
        return noticiaRepository.save(noticia);
    }


    public Noticia buscarPorLink(String link) {
        return noticiaRepository.findById(link)
                .orElseThrow(() -> new ResourceNotFoundException("Notícia não encontrada com o link: " + link));
    }

    public Long getNoticiaTTL(String link) {
        String redisKey = "noticia:" + link;

        // Retorna o tempo de expiração em segundos.
        Long ttl = redisTemplate.getExpire(redisKey, TimeUnit.SECONDS);

        // Retorna o TTL ou -1 (chave não existe ou não tem expiração)
        return (ttl != null) ? ttl : -1L;
    }

    public Iterable<Noticia> buscarTodas() {
        return noticiaRepository.findAll();
    }

    public void deletarPorLink(String link) {
        if (!noticiaRepository.existsById(link)) {
            throw new ResourceNotFoundException("Não foi possível deletar. Notícia não encontrada com o link: " + link);
        }
        noticiaRepository.deleteById(link);
    }
}