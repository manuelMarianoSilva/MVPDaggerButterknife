package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements;

import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.ViewGroup;

import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Model.Movie;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.R;
import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.Root.App;

import java.util.ArrayList;
import java.util.List;


import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements MovieMVP.MainActivityView {

    private final String TAG = MainActivity.class.getName();

    @Inject
    MovieMVP.MainActivityPresenter presenter;

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.activity_main_root_view)
    ViewGroup root;

    private MovieListAdapter adapter;
    private List<CustomViewModel> movieList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        ((App) getApplication()).getComponent().inject(this);

        adapter = new MovieListAdapter(movieList);
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(MainActivity.this, DividerItemDecoration.HORIZONTAL));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.setView(this);
        presenter.loadData();
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.rxJavaUnsubscribe();
        movieList.clear();
        adapter.notifyDataSetChanged();
    }

    @Override
    public void updateData(CustomViewModel customViewModel) {
        movieList.add(customViewModel);
        adapter.notifyItemChanged(movieList.size() - 1);
        Log.d(TAG, "Nueva Movie: " +  customViewModel.getName());
    }

    @Override
    public void showSnackBar(String message) {
        Snackbar.make(root, message, Snackbar.LENGTH_SHORT).show();
    }
}
