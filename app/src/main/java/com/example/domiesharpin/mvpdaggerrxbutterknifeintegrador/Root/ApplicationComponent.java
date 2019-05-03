package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Root;


import android.app.Application;

import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http.OMDbAPIModule;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Http.TMDbAPIModule;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements.MainActivity;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements.MainActivityModule;

import javax.inject.Singleton;

import dagger.Component;


@Singleton
@Component(modules = {ApplicationModule.class, MainActivityModule.class, TMDbAPIModule.class, OMDbAPIModule.class})
public interface ApplicationComponent {

    void inject(MainActivity mainActivity);
}
