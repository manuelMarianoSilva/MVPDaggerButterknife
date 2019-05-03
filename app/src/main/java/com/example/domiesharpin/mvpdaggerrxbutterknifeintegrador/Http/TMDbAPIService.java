package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http;

import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.MovieContainer;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface TMDbAPIService {

    @GET("movie/top_rated")
    Observable<MovieContainer> getTopRatedMovies(@Query("api_key") String apiKey,
                                                 @Query("language") String language,
                                                 @Query("page") Integer page);

}
