package com.example.apiredis.dto;

import jakarta.validation.constraints.NotNull;

import java.util.Date;

public class NoticiaRequestDTO {
    @NotNull(message = "Link da notícia é obrigatório")
    private String link;
    @NotNull(message = "Nome da notícia é obrigatório")
    private String nome;
    private Date data;
    private String descricao;

    public NoticiaRequestDTO(String link, String nome, Date data, String descricao) {
        this.link = link;
        this.nome = nome;
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

    public @NotNull(message = "Nome da notícia é obrigatório") String getNome() {
        return nome;
    }

    public void setNome(@NotNull(message = "Nome da notícia é obrigatório") String nome) {
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
}
