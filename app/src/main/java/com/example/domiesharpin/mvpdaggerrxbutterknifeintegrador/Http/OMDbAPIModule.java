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
public class OMDbAPIModule {

    public final String BASE_OMDB_URL = "http://www.omdbapi.com/";


    @Provides
    public OkHttpClient providesOKHTTPClient (){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return new OkHttpClient.Builder().addInterceptor(interceptor).build();
        /*return new OkHttpClient.Builder().addInterceptor(interceptor).addInterceptor(new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                HttpUrl url = request.url().newBuilder().addQueryParameter("apikey", "d46ceacd").build();
                request = request.newBuilder().url(url).build();
                return chain.proceed(request);
            }
        }).build();*/
    }


    @Provides
    public Retrofit providesRetrofit(String baseURL, OkHttpClient okHttpClient){
        return new Retrofit.Builder()
                .baseUrl(baseURL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();
    }

    @Provides
    public OMDbAPIService providesOMDBAPIModule(){
        return providesRetrofit(BASE_OMDB_URL, providesOKHTTPClient()).create(OMDbAPIService.class);
    }
}
