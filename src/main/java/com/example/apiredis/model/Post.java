package com.example.apiredis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Post")
public class Post {

    @Id
    private String id; // chave única Redis -> post:{id_post}:user:{id_user}

    private String id_post;
    private String id_user;
    private Boolean ativo; // se o post está salvo ou não

    // Construtores
    public Post() {}

    public Post(String id_post, String id_user, Boolean ativo) {
        this.id_post = id_post;
        this.id_user = id_user;
        this.ativo = ativo;
        this.id = "post:" + id_post + ":user:" + id_user; // chave única Redis
    }

    // Getters e Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
        this.id = "post:" + id_post + ":user:" + this.id_user; // atualiza a chave
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
        this.id = "post:" + this.id_post + ":user:" + id_user; // atualiza a chave
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    // toString
    @Override
    public String toString() {
        return "Post{" +
                "id='" + id + '\'' +
                ", id_post='" + id_post + '\'' +
                ", id_user='" + id_user + '\'' +
                ", ativo=" + ativo +
                '}';
    }
}
