package com.example.apiredis.dto;

import com.example.apiredis.model.Noticia;
import java.util.Date;

public class NoticiaResponseDTO {

    private String link;
    private String nome;
    private Date data;
    private String descricao;
    private Long ttlEmSegundos; // tempo de vida no Redis

    // Construtor baseado no objeto Model
    public NoticiaResponseDTO(Noticia noticia) {
        this.link = noticia.getLink();
        this.nome = noticia.getNome();
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

    public Long getTtlEmSegundos() {
        return ttlEmSegundos;
    }

    public void setTtlEmSegundos(Long ttlEmSegundos) {
        this.ttlEmSegundos = ttlEmSegundos;
    }
}