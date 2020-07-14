package com.example.rxjava_retrofit_demo1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Scheduler;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.click_me_BN)
    Button clickMeBN;
    @Bind(R.id.result)
    TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout);
        ButterKnife.bind(this);
    }
    @OnClick(R.id.click_me_BN)
    public void onClick() {
        getMovie();
    }

    private void getMovie() {
        String baseUrl = "http://10.200.32.49:8080/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        ArticleService articleService=retrofit.create(ArticleService.class);
        articleService.getAll("詹")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<ArticleJson>>() {
                    @Override
                    public void onCompleted() {
                        Toast.makeText(MainActivity.this, "Get Article Completed", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        result.setText(e.getMessage());
                    }

                    @Override
                    public void onNext(List<ArticleJson> articleJsons) {

                        for(ArticleJson articleJson : articleJsons)
                        {
                            result.setText(articleJson.getTitle());
                        }
                    }
                });
        /*Call<List<ArticleJson>> call=articleService.getAll("詹");
        call.enqueue(new Callback<List<ArticleJson>>() {
            @Override
            public void onResponse(Call<List<ArticleJson>> call, Response<List<ArticleJson>> response) {
                List<ArticleJson> articleJsons=response.body();
                for(ArticleJson articleJson : articleJsons)
                {
                    result.setText(articleJson.getTitle());
                }
            }

            @Override
            public void onFailure(Call<List<ArticleJson>> call, Throwable t) {

            }
        });*/



    }
}
