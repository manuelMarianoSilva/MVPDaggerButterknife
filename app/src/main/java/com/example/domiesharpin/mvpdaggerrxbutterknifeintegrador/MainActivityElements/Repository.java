package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements;


import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.Movie;

import io.reactivex.Observable;

public interface Repository {

   Observable<Movie> getMoviesFromTMDb();
   Observable<Movie> getResultFromNetwork();
   Observable<Movie> getResultFromCache();


   Observable<String> getCountry();
   Observable<String> getCountryFromNetwork();
   Observable<String> getCountryFromCache();


}
