package com.example.apiredis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;


@RedisHash(value = "noticia_hash")
public class Noticia {

    // O ID é a chave hash completa (ex: news:123456)
    @Id
    private String id;

    private String link; // Este campo é usado para gerar o ID
    private String titulo;
    private Date data;
    private String descricao;

    // Construtores
    public Noticia() {}

    public Noticia(String link, String titulo, Date data, String descricao) {
        this.link = link;
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
    }


    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) { // Corrigi o setNome para setTitulo
        this.titulo = titulo;
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
        return "Noticia [id=" + id + ", link=" + link + ", titulo=" + titulo + ", data=" + data + ", descricao=" + descricao + "]";
    }
}