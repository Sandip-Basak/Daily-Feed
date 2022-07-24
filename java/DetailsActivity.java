package com.example.dailyfeed;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.dailyfeed.Models.NewsHeadLines;
import com.squareup.picasso.Picasso;

public class DetailsActivity extends AppCompatActivity {
    NewsHeadLines headLines;
    TextView txt_title, txt_author, txt_time, txt_detail, txt_content;
    ImageView img_news;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);

        txt_title = findViewById(R.id.text_detail_title);
        txt_author = findViewById(R.id.text_detail_author);
        txt_time = findViewById(R.id.text_detail_time);
        txt_detail = findViewById(R.id.text_detail);
        txt_content = findViewById(R.id.text_detail_content);
        img_news = findViewById(R.id.image_details_news);

        headLines = (NewsHeadLines) getIntent().getSerializableExtra("data");

        txt_title.setText(headLines.getTitle());
        txt_author.setText(headLines.getAuthor());
        txt_time.setText(headLines.getPublishedAt());
        txt_detail.setText(headLines.getDescription());
        txt_content.setText(headLines.getContent());
        Picasso.get().load(headLines.getUrlToImage()).into(img_news);
    }
}