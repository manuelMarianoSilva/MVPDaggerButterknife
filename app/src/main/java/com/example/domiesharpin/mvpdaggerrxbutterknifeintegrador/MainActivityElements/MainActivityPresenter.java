package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements;


import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.Movie;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.Nullable;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivityPresenter implements MovieMVP.MainActivityPresenter {

    @Nullable
    private MovieMVP.MainActivityView view;
    private MovieMVP.MainActivityModel model;
    private Disposable subscription = null;

    public MainActivityPresenter(MovieMVP.MainActivityModel model) {
        this.model = model;
    }

    @Override
    public void setView(MovieMVP.MainActivityView view) {
        this.view = view;
    }

    @Override
    public void loadData() {
        subscription = model.result()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<CustomViewModel>() {
                    @Override
                    public void onNext(CustomViewModel customViewModel) {
                        if (view != null){
                            view.updateData(customViewModel);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        e.printStackTrace();
                        if (view != null){
                            view.showSnackBar("Error en descarga");
                        }
                    }

                    @Override
                    public void onComplete() {
                        if (view != null){
                            view.showSnackBar("Carga completa");
                        }
                    }
                });
    }

    @Override
    public void rxJavaUnsubscribe() {
        if (subscription != null && !subscription.isDisposed()){
            subscription.dispose();
        }
    }
}
