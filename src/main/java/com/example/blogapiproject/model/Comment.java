package com.example.blogapiproject.model;

public class Comment {
    private int id;
    private String authorName;
    private String authorEmail;
    private String authorCountry;
    private String content;
    private int blogId;
    private int rating;

    public Comment(int id, String authorName, String authorEmail, String authorCountry, String content, int blogId, int rating) {
        this.id = id;
        this.authorName = authorName;
        this.authorEmail = authorEmail;
        this.authorCountry = authorCountry;
        this.content = content;
        this.blogId = blogId;
        this.setRating(rating);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorEmail() {
        return authorEmail;
    }

    public void setAuthorEmail(String authorEmail) {
        this.authorEmail = authorEmail;
    }

    public String getAuthorCountry() {
        return authorCountry;
    }

    public void setAuthorCountry(String authorCountry) {
        this.authorCountry = authorCountry;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getBlogId() {
        return blogId;
    }

    public void setBlogId(int blogId) {
        this.blogId = blogId;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        if (rating < 0 || rating > 10) {
            throw new IllegalArgumentException("El rating debe estar entre 0 y 10.");
        }
        this.rating = rating;
    }
}
