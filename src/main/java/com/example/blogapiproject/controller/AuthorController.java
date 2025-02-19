package com.example.blogapiproject.controller;

import com.example.blogapiproject.model.Author;
import com.example.blogapiproject.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    @Autowired
    private AuthorService authorService;

    @PostMapping
    public String createAuthor(@RequestBody Author author) throws IOException {
        return authorService.createAuthor(author);
    }

    @GetMapping
    public List<Author> getAllAuthors() throws IOException {
        return authorService.getAllAuthors();
    }

    @GetMapping("/{id}")
    public Author getAuthorById(@PathVariable int id) throws IOException {
        return authorService.getAuthorById(id);
    }
}
