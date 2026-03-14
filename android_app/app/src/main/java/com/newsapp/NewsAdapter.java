package com.newsapp;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {
    private List<NewsArticle> articles;

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

        holder.readMoreText.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), NewsDetailActivity.class);
            intent.putExtra("url", article.getUrl());
            intent.putExtra("title", article.getTitle());
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView titleText;
        TextView descriptionText;
        TextView sourceText;
        TextView readMoreText;

        ViewHolder(View itemView) {
            super(itemView);
            titleText = itemView.findViewById(R.id.titleText);
            descriptionText = itemView.findViewById(R.id.descriptionText);
            sourceText = itemView.findViewById(R.id.sourceText);
            readMoreText = itemView.findViewById(R.id.readMoreText);
        }
    }
}
