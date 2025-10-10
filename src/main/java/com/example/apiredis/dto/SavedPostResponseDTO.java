package com.example.apiredis.dto;

public class SavedPostResponseDTO {

    private final String postId;
    private final String userId;
    private final Boolean ativo;

    // Construtor
    public SavedPostResponseDTO(String postId, String userId, Boolean ativo) {
        this.postId = postId;
        this.userId = userId;
        this.ativo = ativo;
    }

    // Getters
    public String getPostId() {
        return postId;
    }

    public String getUserId() {
        return userId;
    }

    public Boolean getAtivo() {
        return ativo;
    }
}