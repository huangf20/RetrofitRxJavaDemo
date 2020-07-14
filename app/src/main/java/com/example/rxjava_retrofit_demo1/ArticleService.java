package com.example.rxjava_retrofit_demo1;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ArticleService {
    @GET("android/ArticleServlet")
    Observable<List<ArticleJson>> getAll(@Query("word") String word);
}
