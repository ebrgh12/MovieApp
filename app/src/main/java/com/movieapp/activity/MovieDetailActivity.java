package com.movieapp.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;

import com.movieapp.R;
import com.squareup.picasso.Picasso;

/**
 * Created by giris on 2/12/2017.
 */

public class MovieDetailActivity extends AppCompatActivity {

    ImageView bannerImage;
    TextView movieTitle,movieUserRating,movieReleaseDate,movieSynopsis;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_detail_page);

        Intent intent = getIntent();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bannerImage = (ImageView) findViewById(R.id.poster_image);
        movieTitle = (TextView) findViewById(R.id.movie_title);
        movieUserRating = (TextView) findViewById(R.id.user_rating);
        movieReleaseDate = (TextView) findViewById(R.id.release_date);
        movieSynopsis = (TextView) findViewById(R.id.movie_synopsis);

        movieTitle.setText(intent.getStringExtra("movieName"));
        movieUserRating.setText(String.valueOf(intent.getDoubleExtra("userRating",0)));
        movieReleaseDate.setText(intent.getStringExtra("releaseDate"));
        movieSynopsis.setText(intent.getStringExtra("description"));

        Picasso.with(this)
                .load(intent.getStringExtra("image_name"))
                .error(R.drawable.error_gallery_image)
                .into(bannerImage);

    }

}
