package com.example.blogapiproject.controller;

import com.example.blogapiproject.model.BlogUpdateHistory;
import com.example.blogapiproject.service.BlogUpdateHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/blog-update-history")
public class BlogUpdateHistoryController {
    @Autowired
    private BlogUpdateHistoryService blogUpdateHistoryService;

    // Obtener el historial de actualizaciones de un blog
    @GetMapping("/{blogId}")
    public ResponseEntity<List<BlogUpdateHistory>> getUpdateHistoryByBlogId(@PathVariable int blogId) {
        try {
            List<BlogUpdateHistory> updateHistory = blogUpdateHistoryService.getUpdateHistoryByBlogId(blogId);
            return ResponseEntity.ok(updateHistory);
        } catch (IOException ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
