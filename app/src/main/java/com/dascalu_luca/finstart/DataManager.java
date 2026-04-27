package com.dascalu_luca.finstart;

import android.content.Context;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonArray;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DataManager {

    private static DataManager instance;
    private List<Article> articles;
    private List<String> quotes;

    // Singleton - o singura instanta in toata aplicatia
    public static DataManager getInstance(Context context) {
        if (instance == null) {
            instance = new DataManager(context);
        }
        return instance;
    }

    private DataManager(Context context) {
        loadData(context);
    }

    private void loadData(Context context) {
        try {
            InputStream is = context.getResources().openRawResource(R.raw.articles);
            InputStreamReader reader = new InputStreamReader(is);

            Gson gson = new Gson();
            JsonObject root = gson.fromJson(reader, JsonObject.class);

            // Citeste quote-urile
            quotes = new ArrayList<>();
            JsonArray quotesArray = root.getAsJsonArray("quotes");
            for (int i = 0; i < quotesArray.size(); i++) {
                quotes.add(quotesArray.get(i).getAsString());
            }

            // Citeste articolele
            articles = new ArrayList<>();
            JsonArray articlesArray = root.getAsJsonArray("articles");
            for (int i = 0; i < articlesArray.size(); i++) {
                Article article = gson.fromJson(articlesArray.get(i), Article.class);
                articles.add(article);
            }

            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
            articles = new ArrayList<>();
            quotes = new ArrayList<>();
        }
    }

    public List<Article> getAllArticles() {
        return articles;
    }

    public List<Article> getArticlesByCategory(String category) {
        List<Article> result = new ArrayList<>();
        for (Article article : articles) {
            if (article.getCategory().equals(category)) {
                result.add(article);
            }
        }
        return result;
    }

    public Article getFeaturedArticle() {
        for (Article article : articles) {
            if (article.isFeatured()) return article;
        }
        return articles.isEmpty() ? null : articles.get(0);
    }

    public String getRandomQuote() {
        if (quotes.isEmpty()) return "";
        return quotes.get(new Random().nextInt(quotes.size()));
    }

    public List<String> getCategories() {
        List<String> categories = new ArrayList<>();
        for (Article article : articles) {
            if (!categories.contains(article.getCategory())) {
                categories.add(article.getCategory());
            }
        }
        return categories;
    }
}