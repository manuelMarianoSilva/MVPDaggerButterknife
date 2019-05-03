package com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.MainActivityElements;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.example.domiesharpin.mvpdaggerrxbutterknifeintegrador.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MovieListAdapter extends RecyclerView.Adapter {


    private List<CustomViewModel> movieList;

    public MovieListAdapter(List<CustomViewModel> movieList) {
        this.movieList = movieList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.movie_list_item, viewGroup, false);
        MovieListItemViewHolder viewHolder = new MovieListItemViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
        CustomViewModel movie = movieList.get(i);
        MovieListItemViewHolder holder = (MovieListItemViewHolder) viewHolder;
        holder.fillCell(movie);

    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }



/**************************************VIEWHOLDER**********************************************/



    class MovieListItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.nombreDeLaPeliculaEnCelda)
        EditText movieName;

        @BindView(R.id.paisDeOrigenEnCelda)
        EditText movieOrigin;


        public MovieListItemViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }


        public void fillCell(CustomViewModel movie) {
            movieName.setText(movie.getName());
            movieOrigin.setText(movie.getCountry());
        }
    }


}
