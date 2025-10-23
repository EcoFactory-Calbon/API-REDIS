package com.example.apiredis.model;

import java.io.Serializable;

public class Noticia implements Serializable {

    private String titulo;
    private String link;
    private String descricao;
    private String data;

    public Noticia() {
    }

    public Noticia(String titulo, String link, String descricao, String data) {
        this.titulo = titulo;
        this.link = link;
        this.descricao = descricao;
        this.data = data;
    }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

}