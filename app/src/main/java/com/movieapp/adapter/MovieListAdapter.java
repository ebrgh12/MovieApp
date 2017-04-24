package com.movieapp.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.movieapp.R;
import com.movieapp.activity.MainActivity;
import com.movieapp.model.parsingModel.MovieListDataModel;
import com.movieapp.model.responseModel.popularMovies.PopularMain;
import com.movieapp.viewHolder.MovieListViewHolder;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Response;

/**
 * Created by giris on 2/11/2017.
 */

public class MovieListAdapter extends RecyclerView.Adapter<MovieListViewHolder> {
    Activity activity;
    MovieListViewHolder movieListViewHolder;
    MainActivity mainActivity;
    List<MovieListDataModel> movieListDataModels;

    public MovieListAdapter(Activity activity,
                            MainActivity mainActivity,
                            List<MovieListDataModel> movieListDataModels) {
        this.activity = activity;
        this.mainActivity = mainActivity;
        this.movieListDataModels = movieListDataModels;

    }

    @Override
    public MovieListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.movie_list_item, parent, false);
        movieListViewHolder = new MovieListViewHolder(view);
        return movieListViewHolder;
    }

    @Override
    public void onBindViewHolder(MovieListViewHolder holder, final int position) {
        Picasso.with(activity)
                .load("http://image.tmdb.org/t/p/w185/"+movieListDataModels.get(position).getImagePath())
                .error(R.drawable.error_gallery_image)
                .into(holder.tvItemImage);

        holder.tvItemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mainActivity.callDetailPage(
                        "http://image.tmdb.org/t/p/w342/"+movieListDataModels.get(position).getImagePath(),
                        movieListDataModels.get(position).getUserRating(),
                        movieListDataModels.get(position).getMovieName(),
                        movieListDataModels.get(position).getDescription(),
                        movieListDataModels.get(position).getReleaseDate());
            }
        });
    }

    @Override
    public int getItemCount() {
        return movieListDataModels.size();
    }

}
