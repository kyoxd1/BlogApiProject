package com.example.blogapiproject.controller;

import com.example.blogapiproject.model.Comment;
import com.example.blogapiproject.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    // Agregar un comentario a un blog
    @PostMapping("/{blogId}")
    public ResponseEntity<String> addComment(@PathVariable int blogId, @RequestBody Comment comment) {
        try {
            String result = commentService.addComment(blogId, comment);
            return ResponseEntity.ok(result);
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al procesar la solicitud.");
        }
    }

    // Obtener todos los comentarios de un blog
    @GetMapping("/{blogId}")
    public ResponseEntity<List<Comment>> getCommentsByBlogId(@PathVariable int blogId) {
        try {
            List<Comment> comments = commentService.getCommentsByBlogId(blogId);
            return ResponseEntity.ok(comments);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}