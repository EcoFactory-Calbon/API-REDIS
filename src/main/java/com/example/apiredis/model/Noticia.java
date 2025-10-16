package com.example.apiredis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;

@RedisHash("news")
public class Noticia {

    @Id
    private Long idHash;

    private String link;

    private String titulo;
    private String descricao;
    private Date data;

    public Noticia() {
    }

    public Noticia(Long idHash,String link, String titulo, String descricao, Date data) {
        this.idHash = idHash;
        this.link = link;
        this.titulo = titulo;
        this.descricao = descricao;
        this.data = data;
    }

    public Long getIdHash() {
        return idHash;
    }

    public void setIdHash(Long idHash) {
        this.idHash = idHash;
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

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

}
