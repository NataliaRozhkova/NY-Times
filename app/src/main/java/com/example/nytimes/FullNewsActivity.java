package com.example.nytimes;

import android.content.pm.LabeledIntent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.request.RequestOptions;
import com.example.nytimes.data.NewsItem;

import java.text.SimpleDateFormat;
import java.util.Locale;

public class FullNewsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_item_layout);
        NewsItem newsItem = getIntent().getParcelableExtra("NEWS_ITEM");

        setInformation(newsItem);
    }

    void setInformation(final NewsItem newsItem) {
        setCategory(newsItem);
        setImage(newsItem);
        setTitle(newsItem);
        setDate(newsItem);
        setFullText(newsItem);

    }

    void setImage(final NewsItem newsItem) {
        ImageView image = findViewById(R.id.image);
        RequestOptions imageOption = new RequestOptions()
                .placeholder(R.drawable.ic_launcher_background)
                .fallback(R.drawable.ic_launcher_background)
                .centerCrop();
        RequestManager imageLoader = Glide.with(this).applyDefaultRequestOptions(imageOption);
        imageLoader.load(newsItem.getImageUrl()).into(image);
    }


    void setCategory(final NewsItem newsItem) {
        CharSequence category = newsItem.getCategory().getName();
        setTitle(category);

    }

    void setTitle(final NewsItem newsItem) {
        TextView title = findViewById(R.id.title);
        title.setText(newsItem.getTitle());
    }

    void setDate(final NewsItem newsItem) {
        TextView date = findViewById(R.id.published_date);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MMM d, K:mm a", Locale.ENGLISH);
        date.setText(simpleDateFormat.format(newsItem.getPublishDate()));

    }

    void setFullText(final NewsItem newsItem) {
        TextView text = findViewById(R.id.full_text);
        text.setText(newsItem.getFullText());
    }


}