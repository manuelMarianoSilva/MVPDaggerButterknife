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

        return Observable.zip(
                source.getMoviesFromTMDb(),
                source.getCountry(),
                (movie, s) -> new CustomViewModel(movie.getTitle(), s));
    }
}
