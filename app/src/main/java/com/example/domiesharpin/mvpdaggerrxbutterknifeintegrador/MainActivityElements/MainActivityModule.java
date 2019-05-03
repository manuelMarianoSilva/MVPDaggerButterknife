package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements;

import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http.OMDbAPIService;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http.TMDbAPIService;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class MainActivityModule {

    @Provides
    public MovieMVP.MainActivityPresenter providesMainActivityPresenter(MovieMVP.MainActivityModel model){
        return new MainActivityPresenter(model);
    }

    @Provides
    public MovieMVP.MainActivityModel providesMainActivityModel(Repository source){
        return new MainActivityModel(source);
    }

    @Singleton
    @Provides
    public Repository providesDataSource(TMDbAPIService tmDbAPIService, OMDbAPIService omDbAPIService){
        return new MovieRespository(tmDbAPIService, omDbAPIService);
    }

}
