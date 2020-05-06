package com.example.nytimes;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TableLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.nytimes.data.DataUtils;

public class NewsListActivity extends AppCompatActivity {

    private final NewsListAdapter.OnItemClickListener clickListener = newsItem -> {
        Intent fullNewsActivity = new Intent(this, FullNewsActivity.class);
        fullNewsActivity.putExtra("NEWS_ITEM", newsItem);

        startActivity(fullNewsActivity);
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setDisplayHomeAsUpEnabled(true);
        }
        RecyclerView list = findViewById(R.id.recycler);
        list.setAdapter(new NewsListAdapter(this, DataUtils.generateNews(), clickListener));
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            list.setLayoutManager(new GridLayoutManager(this, 2));
//            list.addItemDecoration(new NewsItemDecoration(this, 0, 12));

        } else {

            list.setLayoutManager(new LinearLayoutManager(this));
//            list.addItemDecoration(new NewsItemDecoration(this, 1, 4));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem menuItem) {

        startActivity(new Intent(this, AboutAuthorActivity.class));
        return true;

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }


}
