package com.example.apiredis.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class NoticiaRequestDTO {
    @NotNull(message = "Link da notícia é obrigatório")
    private String link;
    @NotNull(message = "Nome da notícia é obrigatório")
    private String titulo;
    private Date data;
    private String descricao;

    public NoticiaRequestDTO(String link, String titulo, Date data, String descricao) {
        this.link = link;
        this.titulo = titulo;
        this.data = data;
        this.descricao = descricao;
    }

    public NoticiaRequestDTO() {}

    public @NotNull(message = "Link da notícia é obrigatório") String getLink() {
        return link;
    }

    public void setLink(@NotNull(message = "Link da notícia é obrigatório") String link) {
        this.link = link;
    }

    public @NotNull(message = "O titulo da notícia é obrigatório") String getNome() {
        return titulo;
    }

    public void setTitulo(@NotNull(message = "O titulo da notícia é obrigatório") String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
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


}
