package com.example.blogapiproject.model;

import java.util.ArrayList;
import java.util.List;

public class Blog {
    private int id;
    private int authorId;
    private String title;
    private String theme;
    private Periodicity periodicity;
    private boolean allowComments;
    private List<Comment> comments;
    private List<BlogUpdateHistory> updateHistory;

    public Blog(int id, int authorId, String title, String theme, Periodicity periodicity, boolean allowComments, List<Comment> comments, List<BlogUpdateHistory> updateHistory) {
        this.id = id;
        this.authorId = authorId;
        this.title = title;
        this.theme = theme;
        this.periodicity = periodicity;
        this.allowComments = allowComments;
        this.comments = comments;
        this.updateHistory = updateHistory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public Periodicity getPeriodicity() {
        return periodicity;
    }

    public void setPeriodicity(Periodicity periodicity) {
        this.periodicity = periodicity;
    }

    public boolean isAllowComments() {
        return allowComments;
    }

    public void setAllowComments(boolean allowComments) {
        this.allowComments = allowComments;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<BlogUpdateHistory> getUpdateHistory() {
        return updateHistory;
    }

    public void setUpdateHistory(List<BlogUpdateHistory> updateHistory) {
        this.updateHistory = updateHistory;
    }
}

