package com.example.dailyfeed;

import com.example.dailyfeed.Models.NewsHeadLines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadLines> list, String message);
    void onError(String message);
}
