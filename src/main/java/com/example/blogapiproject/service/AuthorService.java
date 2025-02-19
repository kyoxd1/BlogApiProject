package com.example.blogapiproject.service;

import com.example.blogapiproject.model.Author;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {
    @Autowired
    private JsonFileService jsonFileService;
    private JSONObject json;

    private JSONArray getAuthorsArray() throws IOException {
        json = jsonFileService.readJsonFile();
        return json.optJSONArray("authors") != null ? json.getJSONArray("authors") : new JSONArray();
    }

    public String createAuthor(Author author) throws IOException {

        JSONArray authors = getAuthorsArray();

        JSONObject newAuthor = new JSONObject();
        newAuthor.put("id", authors.length() + 1);
        newAuthor.put("firstName", author.getFirstName());
        newAuthor.put("lastName", author.getLastName());
        System.out.println(" Fecha de " + author.getFirstName());
        System.out.println(" Fecha de cumple " + author.getLastName());
        System.out.println(" Fecha de cumple " + author.getBirthDate());
        System.out.println(" Fecha de cumple " + author.getCountry());
        System.out.println(" Fecha de cumple " + author.getEmail());
        System.out.println(" Fecha de cumple " + author);
        newAuthor.put("birthdate", author.getBirthDate().format(DateTimeFormatter.ISO_LOCAL_DATE));
        newAuthor.put("country", author.getCountry());
        newAuthor.put("email", author.getEmail());

        authors.put(newAuthor);
        json.put("authors", authors);
        jsonFileService.writeJsonFile(json);

        return "El autor fue creado correctamente";
    }

    public List<Author> getAllAuthors() throws IOException {
        JSONArray authors = getAuthorsArray();

        List<Author> authorList = new ArrayList<>();
        for (int i = 0; i < authors.length(); i++) {
            JSONObject authorJson = authors.getJSONObject(i);
            Author author = new Author(
                    authorJson.getInt("id"),
                    authorJson.getString("firstName"),
                    authorJson.getString("lastName"),
                    LocalDate.parse(authorJson.getString("birthdate")),
                    authorJson.getString("country"),
                    authorJson.getString("email")
            );
            authorList.add(author);
        }

        return authorList;
    }

    public Author getAuthorById(int id) throws IOException {
        JSONArray authors = getAuthorsArray();

        for (int i = 0; i < authors.length(); i++) {
            JSONObject authorJson = authors.getJSONObject(i);
            if (authorJson.getInt("id") == id) {
                return new Author(
                        authorJson.getInt("id"),
                        authorJson.getString("firstName"),
                        authorJson.getString("lastName"),
                        LocalDate.parse(authorJson.getString("birthdate")),
                        authorJson.getString("country"),
                        authorJson.getString("email")
                );
            }
        }

        return null;
    }
}
