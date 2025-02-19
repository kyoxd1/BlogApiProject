package com.example.blogapiproject.service;

import com.example.blogapiproject.model.Blog;
import com.example.blogapiproject.model.BlogUpdateHistory;
import com.example.blogapiproject.model.Comment;
import com.example.blogapiproject.model.Periodicity;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogService {

    @Autowired
    private JsonFileService jsonFileService;
    private JSONObject json;

    private JSONArray getJsonArrayBlog() throws IOException{
        json = jsonFileService.readJsonFile();
        return json.optJSONArray("blogs") != null ? json.getJSONArray("blogs") : new JSONArray();
    }

    public String createBlog(Blog blog) throws IOException {
        JSONArray blogs = getJsonArrayBlog();

        JSONObject newBlog = new JSONObject();
        newBlog.put("id", blogs.length() + 1);
        newBlog.put("authorId", blog.getAuthorId());
        newBlog.put("title", blog.getTitle());
        newBlog.put("theme", blog.getTheme());
        newBlog.put("periodicity", blog.getPeriodicity().name());
        newBlog.put("allowComments", blog.isAllowComments());
        newBlog.put("comments", new JSONArray());
        newBlog.put("updateHistory", new JSONArray());

        blogs.put(newBlog);
        json.put("blogs", blogs);
        jsonFileService.writeJsonFile(json);

        return "Blog creado correctamente";
    }

    public String addComment(int blogId, Comment comment) throws IOException {

        // validando el rating
        if (comment.getRating() < 0 || comment.getRating() > 10) {
            throw new IllegalArgumentException("El rating debe estar entre 0 y 10.");
        }

        JSONArray blogs = getJsonArrayBlog();

        for (int i = 0; i < blogs.length(); i++) {
            JSONObject blogJson = blogs.getJSONObject(i);
            if (blogJson.getInt("id") == blogId) {

                if (!blogJson.getBoolean("allowComments")) {
                    return "No se admiten comentarios en este blog.";
                }

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

                return "Comentario añadido correctamente";
            }
        }

        return "Blog no encontrado";
    }

    public String updateBlog(int blogId, String updatedContent, Boolean allowComments) throws IOException {
        JSONArray blogs = getJsonArrayBlog();

        for (int i = 0; i < blogs.length(); i++) {
            JSONObject blogJson = blogs.getJSONObject(i);
            if (blogJson.getInt("id") == blogId) {
                if (updatedContent != null) {
                    JSONArray updateHistory = blogJson.getJSONArray("updateHistory");
                    JSONObject newUpdate = new JSONObject();
                    newUpdate.put("blogId", blogId);
                    newUpdate.put("updateDate", LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
                    newUpdate.put("updatedContent", updatedContent);

                    updateHistory.put(newUpdate);
                    blogJson.put("updateHistory", updateHistory);
                }

                if (allowComments != null) {
                    blogJson.put("allowComments", allowComments);
                }

                json.put("blogs", blogs);
                jsonFileService.writeJsonFile(json);

                return "Blog actualizado correctamente";
            }
        }

        return "Blog no encontrado";
    }

    public Blog getBlogById(int id) throws IOException {
        JSONArray blogs = getJsonArrayBlog();

        for (int i = 0; i < blogs.length(); i++) {
            JSONObject blogJson = blogs.getJSONObject(i);
            if (blogJson.getInt("id") == id) {
                List<Comment> comments = new ArrayList<>();
                JSONArray commentsJson = blogJson.getJSONArray("comments");
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

                List<BlogUpdateHistory> updateHistory = new ArrayList<>();
                JSONArray updateHistoryJson = blogJson.getJSONArray("updateHistory");
                for (int j = 0; j < updateHistoryJson.length(); j++) {
                    JSONObject updateJson = updateHistoryJson.getJSONObject(j);
                    updateHistory.add(new BlogUpdateHistory(
                            updateJson.getInt("blogId"),
                            LocalDateTime.parse(updateJson.getString("updateDate")),
                            updateJson.getString("updatedContent")
                    ));
                }

                return new Blog(
                        blogJson.getInt("id"),
                        blogJson.getInt("authorId"),
                        blogJson.getString("title"),
                        blogJson.getString("theme"),
                        Periodicity.valueOf(blogJson.getString("periodicity")),
                        blogJson.getBoolean("allowComments"),
                        comments,
                        updateHistory
                );
            }
        }

        return null;
    }

    public List<Blog> getAllBlogs() throws IOException {
        JSONArray blogs = getJsonArrayBlog();

        List<Blog> blogList = new ArrayList<>();
        for (int i = 0; i < blogs.length(); i++) {
            JSONObject blogJson = blogs.getJSONObject(i);
            List<Comment> comments = new ArrayList<>();
            JSONArray commentsJson = blogJson.getJSONArray("comments");
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

            List<BlogUpdateHistory> updateHistory = new ArrayList<>();
            JSONArray updateHistoryJson = blogJson.getJSONArray("updateHistory");
            for (int j = 0; j < updateHistoryJson.length(); j++) {
                JSONObject updateJson = updateHistoryJson.getJSONObject(j);
                updateHistory.add(new BlogUpdateHistory(
                        updateJson.getInt("blogId"),
                        LocalDateTime.parse(updateJson.getString("updateDate")),
                        updateJson.getString("updatedContent")
                ));
            }

            blogList.add(new Blog(
                    blogJson.getInt("id"),
                    blogJson.getInt("authorId"),
                    blogJson.getString("title"),
                    blogJson.getString("theme"),
                    Periodicity.valueOf(blogJson.getString("periodicity")),
                    blogJson.getBoolean("allowComments"),
                    comments,
                    updateHistory
            ));
        }

        return blogList;
    }

    public List<Blog> getBlogsSortedByRating() throws IOException {
        List<Blog> blogs = getAllBlogs();
        blogs.sort((b1, b2) -> {
            double rating1 = b1.getComments().stream().mapToInt(Comment::getRating).average().orElse(0);
            double rating2 = b2.getComments().stream().mapToInt(Comment::getRating).average().orElse(0);
            return Double.compare(rating2, rating1);
        });

        return blogs;
    }

}
