package com.example.nytimes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.nytimes.data.NewsItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class NewsListAdapter extends RecyclerView.Adapter<NewsListAdapter.ViewHolder> {

    @NonNull
    private final List<NewsItem> news;
    @NonNull
    private final LayoutInflater inflater;
    @Nullable
    private final OnItemClickListener clickListener;
    @NonNull
    private final RequestManager imageLoader;


    public NewsListAdapter(@NonNull Context context, @NonNull List<NewsItem> news, @NonNull OnItemClickListener clickListener) {
        this.news = news;
        this.inflater = LayoutInflater.from(context);
        this.clickListener = clickListener;
        RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .centerCrop();
        this.imageLoader = Glide.with(context).applyDefaultRequestOptions(imageOption);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.news_item_cardview, parent, false), clickListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(news.get(position));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    public interface OnItemClickListener {
        void onItemClick(NewsItem actor);
    }

     class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView category;
        private final TextView title;
        private final TextView previewText;
        private final TextView publishedDate;
        private final ImageView image;

        ViewHolder(@NonNull View itemView, @Nullable final OnItemClickListener listener) {
            super(itemView);
            itemView.setOnClickListener(view -> {
                int posotion = getAdapterPosition();
                if (listener != null && posotion != RecyclerView.NO_POSITION) {
                    listener.onItemClick((news.get(posotion)));
                }
            });
            category = itemView.findViewById(R.id.category);
            title =  itemView.findViewById(R.id.title);
            previewText = itemView.findViewById(R.id.preview_text);
            publishedDate = itemView.findViewById(R.id.published_date);
            image = itemView.findViewById(R.id.image);

        }

        void bind (NewsItem newsItem) {
            imageLoader.load(newsItem.getImageUrl()).into(image);
            category.setText(newsItem.getCategory().getName());
            title.setText(newsItem.getTitle());
            previewText.setText(newsItem.getPreviewText());
            publishedDate.setText(dateToString(newsItem.getPublishDate()));
        }

        String dateToString (final Date date) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, K:mm a", Locale.ENGLISH);
            return simpleDateFormat.format(date);
        }
    }
}
