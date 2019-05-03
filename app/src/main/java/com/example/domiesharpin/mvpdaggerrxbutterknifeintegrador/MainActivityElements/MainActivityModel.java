package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements;


import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.Movie;

import io.reactivex.Observable;
import io.reactivex.functions.BiFunction;

public class MainActivityModel implements MovieMVP.MainActivityModel {

    private Repository source;

    public MainActivityModel(Repository source) {
        this.source = source;
    }

    @Override
    public Observable<CustomViewModel> result() {

        return Observable.zip(source.getMoviesFromTMDb(), source.getCountry(), new BiFunction<Movie, String, CustomViewModel>() {
            @Override
            public CustomViewModel apply(Movie movie, String s) throws Exception {
                return new CustomViewModel(movie.getTitle(), s);
            }
        });
    }
}
