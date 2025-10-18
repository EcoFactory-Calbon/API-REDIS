package com.example.apiredis.repository;

import com.example.apiredis.model.Noticia;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.Cursor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ScanOptions;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Repository
public class NoticiaRepository {

    private final HashOperations<String, String, Noticia> hashOperations;
    private final RedisTemplate<String, Noticia> redisTemplate;


    private final RedisTemplate<String, String> stringRedisTemplate;

    private static final String NEWS_HASH_FIELD = "details";
    public static final String KEY_PREFIX = "news:";

     public NoticiaRepository(RedisTemplate<String, Noticia> redisTemplate, RedisTemplate<String, String> stringRedisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
        this.stringRedisTemplate = stringRedisTemplate;
    }

    public String generateRedisKey(String link) {
        long hash = link.hashCode() & 0xffffffffL;
        return KEY_PREFIX + hash;
    }

    // --- CRUD e Gestão ---

     public Noticia save(Noticia noticia) {
        String key = generateRedisKey(noticia.getLink());
        hashOperations.put(key, NEWS_HASH_FIELD, noticia);
        return noticia;
    }

    public Noticia findByLink(String link) {
        String key = generateRedisKey(link);
        return hashOperations.get(key, NEWS_HASH_FIELD);
    }

    public boolean existsByLink(String link) {
        String key = generateRedisKey(link);
        return redisTemplate.hasKey(key);
    }

    public boolean deleteByLink(String link) {
        String key = generateRedisKey(link);
        Boolean result = redisTemplate.delete(key);
        return result != null && result;
    }


    public List<Noticia> findAll() {
        List<Noticia> noticias = new ArrayList<>();
        ScanOptions options = ScanOptions.scanOptions().match(KEY_PREFIX + "*").count(500).build();

        try (Cursor<String> cursor = redisTemplate.scan(options)) {
            while (cursor.hasNext()) {
                String key = cursor.next();

                if (redisTemplate.type(key) == DataType.HASH) {

                    Noticia noticia = hashOperations.get(key, NEWS_HASH_FIELD);

                    if (noticia != null) {
                        noticias.add(noticia);
                    } else {

                        Map<Object, Object> fields = stringRedisTemplate.opsForHash().entries(key);

                        noticia = mapHashToNoticia(fields);

                        if (noticia != null) {
                            noticias.add(noticia);
                        }
                    }
                } else {
                    System.err.println("NoticiaRepository: Chave ignorada por não ser HASH: " + key);
                }
            }
        }
        return noticias;
    }



    private Noticia mapHashToNoticia(Map<Object, Object> fields) {
        if (fields == null || fields.isEmpty()) {
            return null;
        }

        Noticia noticia = new Noticia();

        noticia.setTitulo(getStringValue(fields.get("titulo")));
        noticia.setLink(getStringValue(fields.get("link")));
        noticia.setDescricao(getStringValue(fields.get("descricao")));
        noticia.setData(getStringValue(fields.get("data")));

         if (noticia.getLink() == null || noticia.getLink().isEmpty()) {
            return null;
        }

        return noticia;
    }

    private String getStringValue(Object value) {
        if (value == null) {
            return null;
        }
        if (value instanceof String) {
            return (String) value;
        }
        // Lida com casos em que o valor do campo é um array de bytes (comum)
        if (value instanceof byte[]) {
            return new String((byte[]) value);
        }
        return value.toString();
    }


    public long deleteAll() {
        Set<String> keys = redisTemplate.keys(KEY_PREFIX + "*");

        if (keys != null && !keys.isEmpty()) {
            return redisTemplate.delete(keys);
        }
        return 0;
    }
}