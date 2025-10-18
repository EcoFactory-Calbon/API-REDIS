package com.example.apiredis.repository;

public class HashUtils {

    // Prefixo da chave Redis
    public static final String KEY_PREFIX = "news:";


    public static String generateRedisKey(String link) {
        // Calcula o hashcode e garante que é positivo (simula & 0xffffffff)
        long hash = link.hashCode() & 0xffffffffL;
        return KEY_PREFIX + hash;
    }
}