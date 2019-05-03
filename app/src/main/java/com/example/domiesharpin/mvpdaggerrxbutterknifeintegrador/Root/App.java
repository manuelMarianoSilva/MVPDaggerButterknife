package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Root;

import android.app.Application;

import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http.OMDbAPIModule;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http.TMDbAPIModule;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements.MainActivityModule;

public class App extends Application {

    private ApplicationComponent component;


    @Override
    public void onCreate() {
        super.onCreate();

        component = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .mainActivityModule(new MainActivityModule())
                .tMDbAPIModule(new TMDbAPIModule())
                .oMDbAPIModule(new OMDbAPIModule())
                .build();

    }

    public ApplicationComponent getComponent() {
        return component;
    }

}
