package com.example.apiredis.service;

import com.example.apiredis.model.Noticia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SavedNewsService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public SavedNewsService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    private String getRedisKey(Long userId) {
        return "saved_news:" + userId;
    }

    // SADD
    public void salvarNoticia(Long userId, String newsId) {
        redisTemplate.opsForSet().add(getRedisKey(userId), newsId);
    }

    // SREM
    public void removerNoticia(Long userId, String newsId) {
        redisTemplate.opsForSet().remove(getRedisKey(userId), newsId);
    }

    // SMEMBERS
    public Set<Object> listarIdsNoticiasSalvas(Long userId) {
        return redisTemplate.opsForSet().members(getRedisKey(userId));
    }

    // SCARD
    public Long contarNoticiasSalvas(Long userId) {
        return redisTemplate.opsForSet().size(getRedisKey(userId));
    }

    // DEL
    public void apagarTodasNoticias(Long userId) {
        redisTemplate.delete(getRedisKey(userId));
    }

    // 🔍 Busca detalhes das notícias salvas
    public List<Map<String, Object>> listarNoticiasDetalhadas(Long userId) {
        Set<Object> ids = listarIdsNoticiasSalvas(userId);
        if (ids == null || ids.isEmpty()) return Collections.emptyList();

        HashOperations<String, String, Object> hashOps = redisTemplate.opsForHash();
        List<Map<String, Object>> noticiasDetalhadas = new ArrayList<>();

        for (Object idObj : ids) {
            String newsId = String.valueOf(idObj);
            String newsKey = "news:" + newsId;

            Map<String, Object> noticiaData = hashOps.entries(newsKey);
            if (noticiaData != null && !noticiaData.isEmpty()) {
                Map<String, Object> resumo = new HashMap<>();
                resumo.put("titulo", noticiaData.get("titulo"));
                resumo.put("descricao", noticiaData.get("descricao"));
                resumo.put("link", noticiaData.get("link"));
                noticiasDetalhadas.add(resumo);
            }
        }

        return noticiasDetalhadas;
    }
}
