package com.example.apiredis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@RedisHash(value = "noticia", timeToLive = 2592000L) // Cache por 30 dias
public class Noticia {
    // Atributos
    @Id
    private String link;

    private String nome;
    private Date data;
    private String descricao; // (opcional)

    // Construtores
    public Noticia() {}

    public Noticia(String link, String nome, Date data, String descricao) {
        this.link = link;
        this.nome = nome;
        this.data = data;
        this.descricao = descricao;
    }

    // Getters e Setters
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Override
    public String toString() {
        return "Noticia [link=" + link + ", nome=" + nome + ", data=" + data + ", descricao=" + descricao + "]";
    }
}
