package com.dry7.a07082016.services;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.dry7.a07082016.models.Category;
import com.dry7.a07082016.models.Product;
import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import rx.Observable;
import rx.schedulers.Schedulers;

//import com.facebook.stetho.okhttp3.StethoInterceptor;

public class RestClient {
    private String host;
    private static Gson realmGson = new GsonBuilder().setExclusionStrategies(new ExclusionStrategy() {
        @Override
        public boolean shouldSkipField(FieldAttributes f) {
            return f.getDeclaringClass().equals("");
        }

        @Override
        public boolean shouldSkipClass(Class<?> clazz) {
            return false;
        }
    }).create();

    public RestClient() {}

    public static WebServiceInterface getInstance()
    {
        RxJavaCallAdapterFactory rxAdapter = RxJavaCallAdapterFactory.createWithScheduler(Schedulers.io());
        OkHttpClient client = new OkHttpClient.Builder()
                //.addNetworkInterceptor(new StethoInterceptor())
        .build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.gifts48.ru/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(RestClient.realmGson))
                .addCallAdapterFactory(rxAdapter).build();

        return retrofit.create(WebServiceInterface.class);
    }

    /**
     * Connection status
     *
     * @param context
     *
     * @return bool
     */
    public static boolean isNewtworkAvailable(Context context)
    {
        ConnectivityManager connectivityManager = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

    public static interface WebServiceInterface
    {
        @GET("products")
        Observable<ArrayList<Product>> productsList();

        @GET("categories")
        Observable<ArrayList<Category>> categoriesList();
    }
}