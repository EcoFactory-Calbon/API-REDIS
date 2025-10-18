package com.example.apiredis.model;

import java.io.Serializable;

public class Noticia implements Serializable {

    // Campos solicitados
    private String titulo;
    private String link;
    private String descricao;
    private String data; // Mantendo como String, mas poderia ser LocalDateTime

    // Construtor vazio
    public Noticia() {
    }

    // Construtor com todos os campos (opcional, para conveniência)
    public Noticia(String titulo, String link, String descricao, String data) {
        this.titulo = titulo;
        this.link = link;
        this.descricao = descricao;
        this.data = data;
    }

    // Getters e Setters
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    // Opcional: toString para debug
    @Override
    public String toString() {
        return "Noticia{titulo='" + titulo + "', link='" + link + "'}";
    }
}