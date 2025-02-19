package com.example.blogapiproject.controller;

import com.example.blogapiproject.dto.UpdateBlogRequest;
import com.example.blogapiproject.model.Blog;
import com.example.blogapiproject.model.Comment;
import com.example.blogapiproject.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/blogs")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping
    public String createBlog(@RequestBody Blog blog) throws IOException {
        return blogService.createBlog(blog);
    }

    @PostMapping("/{id}/comments")
    public ResponseEntity<String> addComment(@PathVariable int id, @RequestBody Comment comment) throws IOException {
        try {
            String result = blogService.addComment(id, comment);
            if (result.equals("No se admiten comentarios en este blog.")){
                return ResponseEntity.status(HttpStatus.FORBIDDEN).body(result);
            }
            return ResponseEntity.ok(result);
        }catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud.");
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBlog(@PathVariable int id, @RequestBody UpdateBlogRequest updateRequest ) throws IOException {
        try {
            String result = blogService.updateBlog(id, updateRequest.getUpdatedContent(), updateRequest.getAllowComments());
            return ResponseEntity.ok(result);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud.");
        }
    }

    @GetMapping("/{id}")
    public Blog getBlogById(@PathVariable int id) throws IOException {
        return blogService.getBlogById(id);
    }

    @GetMapping
    public List<Blog> getAllBlogs() throws IOException {
        return blogService.getAllBlogs();
    }

    @GetMapping("/sorted")
    public List<Blog> getBlogsSortedByRating() throws IOException {
        return blogService.getBlogsSortedByRating();
    }
}
