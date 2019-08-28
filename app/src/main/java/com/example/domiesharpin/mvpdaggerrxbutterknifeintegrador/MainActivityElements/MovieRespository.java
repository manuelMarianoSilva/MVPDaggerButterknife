package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements;


import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http.OMDbAPIService;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http.TMDbAPIService;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.Movie;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.MovieContainer;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.OMDbMovieContainer;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.R;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;

public class MovieRespository implements Repository {

    private TMDbAPIService tmDbAPIService;
    private OMDbAPIService omDbAPIService;

    private List<Movie> movieCache;
    private List<String> countryCache;

    private long lastTimeStamp;

    private static final long CACHE_LIFETIME = 20 * 1000; //esto dura 20 segundos

    public MovieRespository(TMDbAPIService tmDbAPIService, OMDbAPIService omDbAPIService) {
        this.tmDbAPIService = tmDbAPIService;
        this.omDbAPIService = omDbAPIService;

        this.lastTimeStamp = System.currentTimeMillis();
        this.movieCache = new ArrayList<>();
        this.countryCache = new ArrayList<>();
    }

    public boolean isCacheUpdated(){
        return (System.currentTimeMillis() - lastTimeStamp) < CACHE_LIFETIME;
    }

    @Override
    public Observable<Movie> getMoviesFromTMDb() {
        return getResultFromCache().switchIfEmpty(getResultFromNetwork());
    }


    @Override
    public Observable<Movie> getResultFromNetwork() {
        Observable<MovieContainer> movieContainerObservable = tmDbAPIService.getTopRatedMovies("bf55d24e465b3fb8dd1800b20fefff34","es_AR",1)
                .concatWith(tmDbAPIService.getTopRatedMovies("bf55d24e465b3fb8dd1800b20fefff34","es_AR", 2))
                .concatWith(tmDbAPIService.getTopRatedMovies("bf55d24e465b3fb8dd1800b20fefff34","es_AR", 3));


        return movieContainerObservable.concatMap(
                (Function<MovieContainer, ObservableSource<Movie>>) movieContainer ->
                        Observable.fromIterable(movieContainer.getResults())).doOnNext(movie -> movieCache.add(movie));
    }

    @Override
    public Observable<Movie> getResultFromCache() {
        if (isCacheUpdated()){
            return Observable.fromIterable(movieCache);
        } else {
            lastTimeStamp = System.currentTimeMillis();
            movieCache.clear();
            return Observable.empty();
        }
    }


    @Override
    public Observable<String> getCountryFromNetwork() {

        return getMoviesFromTMDb()
                .concatMap((Function<Movie, ObservableSource<OMDbMovieContainer>>) movie ->
                        omDbAPIService.getMovieInfo("d46ceacd", movie.getTitle()))
                            .concatMap((Function<OMDbMovieContainer, ObservableSource<String>>) omDbMovieContainer -> {
                                if (omDbMovieContainer == null || omDbMovieContainer.getCountry() == null) {
                                    return Observable.just("Desconocido");
                                } else {
                                    return Observable.just(omDbMovieContainer.getCountry());
                                }

                            }).doOnNext(s -> countryCache.add(s));
    }

    @Override
    public Observable<String> getCountryFromCache() {
        if (isCacheUpdated()){
            return Observable.fromIterable(countryCache);
        } else {
            lastTimeStamp = System.currentTimeMillis();
            countryCache.clear();
            return Observable.empty();
        }
    }


    @Override
    public Observable<String> getCountry() {
        return getCountryFromCache().switchIfEmpty(getCountryFromNetwork());
    }


}
