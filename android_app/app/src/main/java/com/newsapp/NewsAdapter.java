package com.newsapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsArticle> articles;
    private ExecutorService executor = Executors.newFixedThreadPool(3);

    public NewsAdapter(List<NewsArticle> articles) {
        this.articles = articles;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_news, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        NewsArticle article = articles.get(position);
        holder.titleText.setText(article.getTitle());
        holder.descriptionText.setText(article.getDescription());
        holder.sourceText.setText(article.getSource() + " • " + article.getPublishedAt());

        // Load image if available
        if (article.getImageUrl() != null && !article.getImageUrl().isEmpty()) {
            holder.newsImage.setVisibility(View.VISIBLE);
            loadImage(article.getImageUrl(), holder.newsImage);
        } else {
            holder.newsImage.setVisibility(View.GONE);
        }

        holder.readMoreText.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
            intent.putExtra("url", article.getUrl());
            intent.putExtra("title", article.getTitle());
            v.getContext().startActivity(intent);
        });
    }

    private void loadImage(String imageUrl, ImageView imageView) {
        executor.execute(() -> {
            try {
                URL url = new URL(imageUrl);
                InputStream inputStream = url.openStream();
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                imageView.post(() -> imageView.setImageBitmap(bitmap));
            } catch (Exception e) {
                imageView.post(() -> imageView.setVisibility(View.GONE));
            }
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        ImageView newsImage;
        TextView descriptionText;
        TextView sourceText;
        TextView readMoreText;

        ViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            newsImage = itemView.findViewById(R.id.newsImage);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            sourceText = itemView.findViewById(R.id.sourceText);
            readMoreText = itemView.findViewById(R.id.readMoreText);
        }
    }
}
