package com.movieapp.viewHolder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.movieapp.R;

/**
 * Created by giris on 2/11/2017.
 */

public class MovieListViewHolder extends RecyclerView.ViewHolder {
    public ImageView tvItemImage;

    public MovieListViewHolder(View itemView) {
        super(itemView);
        tvItemImage = (ImageView) itemView.findViewById(R.id.tvItemImage);
    }
}
