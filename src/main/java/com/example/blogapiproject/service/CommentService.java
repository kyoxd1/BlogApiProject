package com.example.blogapiproject.service;

import com.example.blogapiproject.model.Comment;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private JsonFileService jsonFileService;

    public String addComment(int blogId, Comment comment) throws IOException {

        if (comment.getRating() < 0 || comment.getRating() > 10) {
            throw new IllegalArgumentException("El rating debe estar entre 0 y 10.");
        }

        JSONObject json = jsonFileService.readJsonFile();
        JSONArray blogs = json.optJSONArray("blogs") != null ? json.getJSONArray("blogs") : new JSONArray();

        for (int i = 0; i < blogs.length(); i++) {
            JSONObject blogJson = blogs.getJSONObject(i);
            if (blogJson.getInt("id") == blogId) {
                JSONArray comments = blogJson.getJSONArray("comments");
                JSONObject newComment = new JSONObject();
                newComment.put("id", comments.length() + 1);
                newComment.put("authorName", comment.getAuthorName());
                newComment.put("authorEmail", comment.getAuthorEmail());
                newComment.put("authorCountry", comment.getAuthorCountry());
                newComment.put("content", comment.getContent());
                newComment.put("blogId", blogId);
                newComment.put("rating", comment.getRating());

                comments.put(newComment);
                blogJson.put("comments", comments);
                json.put("blogs", blogs);
                jsonFileService.writeJsonFile(json);

                return "Comment added successfully";
            }
        }

        return "Blog not found";
    }

    public List<Comment> getCommentsByBlogId(int blogId) throws IOException {
        JSONObject json = jsonFileService.readJsonFile();
        JSONArray blogs = json.optJSONArray("blogs") != null ? json.getJSONArray("blogs") : new JSONArray();

        for (int i = 0; i < blogs.length(); i++) {
            JSONObject blogJson = blogs.getJSONObject(i);
            if (blogJson.getInt("id") == blogId) {
                JSONArray commentsJson = blogJson.getJSONArray("comments");
                List<Comment> comments = new ArrayList<>();

                for (int j = 0; j < commentsJson.length(); j++) {
                    JSONObject commentJson = commentsJson.getJSONObject(j);
                    comments.add(new Comment(
                            commentJson.getInt("id"),
                            commentJson.getString("authorName"),
                            commentJson.getString("authorEmail"),
                            commentJson.getString("authorCountry"),
                            commentJson.getString("content"),
                            commentJson.getInt("blogId"),
                            commentJson.getInt("rating")
                    ));
                }

                return comments;
            }
        }

        return new ArrayList<>();
    }
}
