package com.example.apiredis.dto;

import com.example.apiredis.model.Noticia;

import java.util.Date;

public class NoticiaResponseDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String link;
    private Date data;
    private Long idHash;

    public NoticiaResponseDTO() {
    }

    public NoticiaResponseDTO(Long id, String titulo, String descricao, String link, Date data, Long idHash) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.link = link;
        this.data = data;
        this.idHash = idHash;
    }

    public NoticiaResponseDTO(Noticia noticia) {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public Long getIdHash() {
        return idHash;
    }

    public void setIdHash(Long idHash) {
        this.idHash = idHash;
    }

}
