package com.newsapp;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private NewsAdapter adapter;
    private List<NewsArticle> articles = new ArrayList<>();
    private ExecutorService executor = Executors.newSingleThreadExecutor();
    private Handler mainHandler = new Handler(Looper.getMainLooper());
    
    // Replace with your actual API key
    private static final String API_KEY = "your_api_key_here";
    private static final String API_URL = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" + API_KEY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new NewsAdapter(articles);
        recyclerView.setAdapter(adapter);

        fetchNews();
    }

    private void fetchNews() {
        executor.execute(() -> {
            try {
                OkHttpClient client = new OkHttpClient();
                Request request = new Request.Builder()
                        .url(API_URL)
                        .build();

                Response response = client.newCall(request).execute();
                if (response.isSuccessful() && response.body() != null) {
                    String jsonData = response.body().string();
                    Gson gson = new Gson();
                    
                    // Parse the response
                    Type responseType = new TypeToken<NewsResponse>(){}.getType();
                    NewsResponse newsResponse = gson.fromJson(jsonData, responseType);
                    
                    if (newsResponse != null && newsResponse.articles != null) {
                        List<NewsArticle> fetchedArticles = new ArrayList<>();
                        for (Article article : newsResponse.articles) {
                            fetchedArticles.add(new NewsArticle(
                                article.title != null ? article.title : "",
                                article.description != null ? article.description : "",
                                article.url != null ? article.url : "",
                                article.urlToImage != null ? article.urlToImage : "",
                                article.publishedAt != null ? article.publishedAt : "",
                                article.source != null && article.source.name != null ? article.source.name : ""
                            ));
                        }
                        
                        mainHandler.post(() -> {
                            articles.clear();
                            articles.addAll(fetchedArticles);
                            adapter.notifyDataSetChanged();
                        });
                    }
                } else {
                    mainHandler.post(() -> 
                        Toast.makeText(MainActivity.this, "Failed to fetch news", Toast.LENGTH_SHORT).show()
                    );
                }
            } catch (IOException e) {
                e.printStackTrace();
                mainHandler.post(() -> 
                    Toast.makeText(MainActivity.this, "Network error: " + e.getMessage(), Toast.LENGTH_SHORT).show()
                );
            }
        });
    }

    // Helper classes for JSON parsing
    private static class NewsResponse {
        String status;
        int totalResults;
        List<Article> articles;
    }

    private static class Article {
        Source source;
        String title;
        String description;
        String url;
        String urlToImage;
        String publishedAt;
    }

    private static class Source {
        String name;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        executor.shutdown();
    }
}
