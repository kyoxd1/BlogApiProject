package com.example.blogapiproject.service;

import com.example.blogapiproject.model.BlogUpdateHistory;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class BlogUpdateHistoryService {
    @Autowired
    private JsonFileService jsonFileService;

    public List<BlogUpdateHistory> getUpdateHistoryByBlogId(int blogId) throws IOException {
        JSONObject json = jsonFileService.readJsonFile();
        JSONArray blogs = json.optJSONArray("blogs") != null ? json.getJSONArray("blogs") : new JSONArray();

        for (int i = 0; i < blogs.length(); i++) {
            JSONObject blogJson = blogs.getJSONObject(i);
            if (blogJson.getInt("id") == blogId) {
                JSONArray updateHistoryJson = blogJson.getJSONArray("updateHistory");
                List<BlogUpdateHistory> updateHistory = new ArrayList<>();

                for (int j = 0; j < updateHistoryJson.length(); j++) {
                    JSONObject updateJson = updateHistoryJson.getJSONObject(j);
                    updateHistory.add(new BlogUpdateHistory(
                            updateJson.getInt("blogId"),
                            LocalDateTime.parse(updateJson.getString("updateDate")),
                            updateJson.getString("updatedContent")
                    ));
                }

                return updateHistory;
            }
        }

        return new ArrayList<>();
    }
}
