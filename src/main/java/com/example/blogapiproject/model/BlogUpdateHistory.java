package com.example.blogapiproject.model;

import java.time.LocalDateTime;

public class BlogUpdateHistory {

    private int blogId;
    private LocalDateTime updateDate;
    private String updatedContent;

    public BlogUpdateHistory(int blogId, LocalDateTime updateDate, String updatedContent) {
        this.blogId = blogId;
        this.updateDate = updateDate;
        this.updatedContent = updatedContent;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getUpdatedContent() {
        return updatedContent;
    }

    public void setUpdatedContent(String updatedContent) {
        this.updatedContent = updatedContent;
    }
}
