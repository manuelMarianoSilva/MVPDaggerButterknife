package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http;


import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.R;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
public class TMDbAPIModule {

    public final String BASE_URL = "https://api.themoviedb.org/3/";


    @Provides
    public OkHttpClient providesOKHTTPClient (){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
        /*return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("api_key", String.valueOf(R.string.tmdb_api_key)).build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();*/
    }


    @Provides
    public Retrofit providesRetrofit(String baseURL, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    public TMDbAPIService providesTMDBAPIService(){
        return providesRetrofit(BASE_URL, providesOKHTTPClient()).create(TMDbAPIService.class);
    }

}
