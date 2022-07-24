package com.example.dailyfeed;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.dailyfeed.Models.NewsApiResponse;
import com.example.dailyfeed.Models.NewsHeadLines;

import java.util.List;

public class MainActivity extends AppCompatActivity implements SelectListener, View.OnClickListener{
    RecyclerView recyclerView;
    CustomAdapter adapter;
    Button b1,b2,b3,b4,b5,b6,b7;
    SearchView searchView;
    ProgressBar bar;
    LinearLayout layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toast.makeText(this, "Created by Sandip Basak", Toast.LENGTH_SHORT).show();
        bar = findViewById(R.id.progress);
        layout = findViewById(R.id.linear);
        searchView = findViewById(R.id.search_view);
        b1 = findViewById(R.id.btn_business);
        b2 = findViewById(R.id.btn_entertainment);
        b3 = findViewById(R.id.btn_general);
        b4 = findViewById(R.id.btn_health);
        b5 = findViewById(R.id.btn_science);
        b6 = findViewById(R.id.btn_sports);
        b7 = findViewById(R.id.btn_technology);
        b1.setOnClickListener(this);
        b2.setOnClickListener(this);
        b3.setOnClickListener(this);
        b4.setOnClickListener(this);
        b5.setOnClickListener(this);
        b6.setOnClickListener(this);
        b7.setOnClickListener(this);
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                bar.setVisibility(View.VISIBLE);
                layout.setVisibility(View.GONE);
                RequestManager requestManager = new RequestManager(MainActivity.this);
                requestManager.getNewsHeadlines(listener, "general", query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsHeadlines(listener, "general", null);
    }

    private final OnFetchDataListener<NewsApiResponse> listener = new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadLines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(MainActivity.this, "No article on the topic found !", Toast.LENGTH_SHORT).show();
            }
            else{
                showNews(list);
            }
            bar.setVisibility(View.GONE);
            layout.setVisibility(View.VISIBLE);
        }

        @Override
        public void onError(String message) {
            Toast.makeText(MainActivity.this, "Some error occurred !", Toast.LENGTH_SHORT).show();
        }
    };

    private void showNews(List<NewsHeadLines> list) {
        recyclerView = findViewById(R.id.recycler_main);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 1));
        adapter = new CustomAdapter(this, list, this);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void OnNewsClicked(NewsHeadLines headLines) {
        startActivity(new Intent(MainActivity.this, DetailsActivity.class)
        .putExtra("data", headLines));
    }

    @Override
    public void onClick(View view) {
        Button button = (Button) view;
        String category = button.getText().toString();
        bar.setVisibility(View.VISIBLE);
        layout.setVisibility(View.GONE);
        RequestManager requestManager = new RequestManager(this);
        requestManager.getNewsHeadlines(listener, category, null);
    }
}