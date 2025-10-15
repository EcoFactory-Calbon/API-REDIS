package com.example.apiredis.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

@RedisHash("Post")
public class Post {

    @Id
    private String id; // post:{id_noticia}:user:{id_user}

    private String id_user;
    private Boolean ativo;
    private Noticia noticia;

    public Post() {}

    public Post(Noticia noticia, String id_user, Boolean ativo) {
        this.noticia = noticia;
        this.id_user = id_user;
        this.ativo = ativo;
        gerarChaveRedis();
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getId_user() { return id_user; }
    public void setId_user(String id_user) { this.id_user = id_user; }

    public Boolean getAtivo() { return ativo; }
    public void setAtivo(Boolean ativo) { this.ativo = ativo; }

    public Noticia getNoticia() { return noticia; }
    public void setNoticia(Noticia noticia) { this.noticia = noticia; }

    public void gerarChaveRedis() {
        if (noticia != null && noticia.getId() != null && id_user != null) {
            this.id = "post:" + noticia.getId() + ":user:" + id_user;
        }
    }
}
