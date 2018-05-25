package com.example.cletrezo.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MovieDetails extends AppCompatActivity {



    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_activity);

        TextView movieTitleTextView = findViewById(R.id.movieTitle);
        ImageView movieImageView = findViewById(R.id.movieImage);
        TextView  movieReleaseDateView = findViewById(R.id.movieReleaseDate);
        TextView  movieRatingView = findViewById(R.id.movieRating);
        TextView movieDescriptionView = findViewById(R.id.movieDescription);


        ArrayList<Movie>movies = MovieDataSource.movieArrayList();//pull movies from datasource

        Intent intent = getIntent(); // get the intent passed from MainActivity
        int positionOfItemClicked = intent.getExtras().getInt(MainActivity.Image_position);
        movieTitleTextView.setText(movies.get(positionOfItemClicked).getMovieTitle()); // set movie title

        Picasso.with(this)
                .load(movies.get(positionOfItemClicked).getMovieImagePath())
                .fit()
                .centerCrop()
                .into(movieImageView);

        movieReleaseDateView.setText(movies.get(positionOfItemClicked).getMovieReleaseDate());
        movieRatingView.setText(String.valueOf(movies.get(positionOfItemClicked).getMovieRating()));
        movieDescriptionView.setText(movies.get(positionOfItemClicked).getMovieDescripton());

    }
}