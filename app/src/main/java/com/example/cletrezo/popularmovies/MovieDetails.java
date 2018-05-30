package com.example.cletrezo.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        getSupportActionBar().setTitle("Movie Detalis");
        TextView movieTitleTextView = findViewById(R.id.movieTitle);
        ImageView movieImageView = findViewById(R.id.movieImage);
        TextView  movieReleaseDateView = findViewById(R.id.movieReleaseDate);
        TextView  movieRatingView = findViewById(R.id.movieRating);
        TextView movieDescriptionView = findViewById(R.id.movieDescription);

        movieTitleTextView.setText(getIntent().getExtras().get(MainActivity.MOVIE_TITLE).toString()); // set movie title

        Picasso.with(this)
                .load(getIntent().getExtras().get(MainActivity.MOVIE_IMAGE).toString())
                .fit()
                .placeholder(R.drawable.progress_file)
                .error(R.drawable.ic_launcher_background)
                .into(movieImageView);

        movieReleaseDateView.setText(getIntent().getExtras().get(MainActivity.MOVIE_RELEASE_DATE).toString());
        movieRatingView.setText(getIntent().getExtras().get(MainActivity.MOVIE_RATING).toString());
        movieDescriptionView.setText(getIntent().getExtras().get(MainActivity.MOVIE_DESCRIPTION).toString());

    }
}