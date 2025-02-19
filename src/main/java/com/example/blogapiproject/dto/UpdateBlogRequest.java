package com.example.blogapiproject.dto;

public class UpdateBlogRequest {
    private String updatedContent;
    private Boolean allowComments;

    // Getters y Setters
    public String getUpdatedContent() { return updatedContent; }
    public void setUpdatedContent(String updatedContent) { this.updatedContent = updatedContent; }

    public Boolean getAllowComments() { return allowComments; }
    public void setAllowComments(Boolean allowComments) { this.allowComments = allowComments; }
}
