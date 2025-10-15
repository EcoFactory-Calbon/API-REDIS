

package com.example.apiredis.model;

import jakarta.validation.constraints.NotNull;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.util.Date;
import java.util.Objects;

@RedisHash("News")
public class Noticia {

    @Id
    private String id; // news:{hash(link) & 0xffffffff}

    private String titulo;
    private String link;
    private String descricao;
    private String data;

    public Noticia(@NotNull(message = "Link da notícia é obrigatório") String link, String titulo, Date data, String descricao) {}

    public Noticia(String titulo, String link, String descricao, String data) {
        this.titulo = titulo;
        this.link = link;
        this.descricao = descricao;
        this.data = data;
        this.id = gerarChave(link);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getLink() { return link; }
    public void setLink(String link) { this.link = link; }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    public static String gerarChave(String link) {
        int hash = Objects.hashCode(link) & 0xffffffff;
        return "news:" + hash;
    }
}
