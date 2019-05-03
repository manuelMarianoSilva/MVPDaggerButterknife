package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http;

import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.OMDbMovieContainer;


import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface OMDbAPIService {


    @GET("/")
    Observable<OMDbMovieContainer> getMovieInfo (@Query("apikey") String apiKey,
                                                 @Query("t") String movieTitle);

}
