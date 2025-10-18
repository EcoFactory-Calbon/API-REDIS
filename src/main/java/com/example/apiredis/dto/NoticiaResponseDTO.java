package com.example.apiredis.dto;

import java.util.Date;

public class NoticiaResponseDTO {
    private String id;
    private String titulo;
    private String descricao;
    private String link;
    private Date data;

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public Date getData() { return data; }
    public void setData(Date data) { this.data = data; }
}
