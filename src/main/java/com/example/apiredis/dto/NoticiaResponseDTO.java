package com.example.apiredis.dto;

import com.example.apiredis.model.Noticia;

public class NoticiaResponseDTO {

    private String link;
    private String titulo;
    private String data;
    private String descricao;
    private Long ttlEmSegundos; // tempo de vida no Redis

    // Construtor baseado no objeto Model
    public NoticiaResponseDTO(Noticia noticia) {
        this.link = noticia.getLink();
        this.titulo = noticia.getTitulo();
        this.data = noticia.getData();
        this.descricao = noticia.getDescricao();
    }

    // Construtor completo
    public NoticiaResponseDTO(Noticia noticia, Long ttlEmSegundos) {
        this(noticia); // Chama o construtor acima
        this.ttlEmSegundos = ttlEmSegundos;
    }

    // Getters e Setters
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

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Long getTtlEmSegundos() {
        return ttlEmSegundos;
    }

    public void setTtlEmSegundos(Long ttlEmSegundos) {
        this.ttlEmSegundos = ttlEmSegundos;
    }
}