package com.example.apiredis.model;


import java.io.Serializable;

public class Post implements Serializable {

    private String id_user;
    private String id_post;
    private Boolean ativo;

    public Post(){}

    public Post(String id_user, String id_post, Boolean ativo) {
        this.id_user = id_user;
        this.id_post = id_post;
        this.ativo = ativo;
    }

    public String getId_user() {
        return id_user;
    }

    public void setId_user(String id_user) {
        this.id_user = id_user;
    }

    public String getId_post() {
        return id_post;
    }

    public void setId_post(String id_post) {
        this.id_post = id_post;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }
}
