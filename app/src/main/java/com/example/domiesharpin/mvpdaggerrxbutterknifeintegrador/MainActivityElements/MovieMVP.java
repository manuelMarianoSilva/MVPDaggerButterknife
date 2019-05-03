package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements;


import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.Movie;

import io.reactivex.Observable;

public interface MovieMVP {


    interface MainActivityView{
        void updateData(CustomViewModel movie);
        void showSnackBar(String message);
    }

    interface MainActivityPresenter{
        void setView(MovieMVP.MainActivityView view);
        void loadData();
        void rxJavaUnsubscribe();


    }

    interface MainActivityModel{
        Observable<CustomViewModel> result();
    }

}
