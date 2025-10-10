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
    private final RedisTemplate<String, Object> redisTemplate;

    private static final String NEWS_KEY_PREFIX = "news:";

    @Autowired
    public NoticiaService(NoticiaRepository noticiaRepository, RedisTemplate<String, Object> redisTemplate) {
        this.noticiaRepository = noticiaRepository;
        this.redisTemplate = redisTemplate;
    }

    private String generateRedisKey(String link) {
        return NEWS_KEY_PREFIX + link;
    }

    public Noticia salvarOuAtualizar(Noticia noticia) {
        noticia.setId(generateRedisKey(noticia.getLink()));

        return noticiaRepository.save(noticia);
    }


    public Noticia buscarPorLink(String link) {
        String redisKey = generateRedisKey(link);

        return noticiaRepository.findById(redisKey)
                .orElseThrow(() -> new ResourceNotFoundException("Notícia não encontrada com o link: " + link));
    }

    public Long getNoticiaTTL(String link) {
        String redisKey = generateRedisKey(link);

        Long ttl = redisTemplate.getExpire(redisKey, TimeUnit.SECONDS);

        return (ttl != null) ? ttl : -2L;
    }

    public Iterable<Noticia> buscarTodas() {
        return noticiaRepository.findAll();
    }

    public void deletarPorLink(String link) {
        String redisKey = generateRedisKey(link);

        if (!noticiaRepository.existsById(redisKey)) {
            throw new ResourceNotFoundException("Não foi possível deletar. Notícia não encontrada com o link: " + link);
        }
        noticiaRepository.deleteById(redisKey);
    }
}