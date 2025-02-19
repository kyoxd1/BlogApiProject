package com.example.blogapiproject.service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Service
public class JsonFileService {
    private static final String FILE_PATH = "data.json";

    public JSONObject readJsonFile() throws IOException {
        File file = new File(FILE_PATH);
        if (!file.exists()) {
            file.createNewFile();
            try (FileWriter writer = new FileWriter(file)) {
                writer.write("{}"); // Escribe un objeto JSON vacío
            }
            return new JSONObject();
        }
        String content = new String(Files.readAllBytes(Paths.get(FILE_PATH)));
        return new JSONObject(content);
    }

    public void writeJsonFile(JSONObject jsonObject) throws IOException {
        try (FileWriter file = new FileWriter(FILE_PATH)) {
            file.write(jsonObject.toString());
        }
    }
}