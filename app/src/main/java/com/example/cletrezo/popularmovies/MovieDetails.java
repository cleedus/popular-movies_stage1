package com.example.cletrezo.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class MovieDetails extends AppCompatActivity {
    final String ratingDenominator="/10";


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);
        TextView movieTitleTextView = findViewById(R.id.movieTitle);
        ImageView movieImageView = findViewById(R.id.movieImage);
        TextView  movieReleaseDateView = findViewById(R.id.movieReleaseDate);
        TextView  movieRatingView = findViewById(R.id.movieRating);
        TextView movieDescriptionView = findViewById(R.id.movieDescription);

        //using parcelable, movie Object was passed using the position clicked.
        Movie movie= getIntent().getParcelableExtra(MainActivity.MOVIE_IN_CURRENT_CLICKED_POSITION);

        movieTitleTextView.setText(movie.getMovieTitle()); // set movie title

        Picasso.with(this)
                .load(movie.getMovieImagePath())
                .fit()
                .placeholder(R.drawable.progress_file)
                .error(R.drawable.ic_launcher_background)
                .into(movieImageView);

        movieReleaseDateView.setText(movie.getMovieReleaseDate());
        movieRatingView.setText(String.format("%s%s", String.valueOf(movie.getMovieRating()),ratingDenominator));
        movieDescriptionView.setText(movie.getMovieDescripton());


    }





    }



