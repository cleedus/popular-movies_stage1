package com.example.cletrezo.popularmovies;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

public class PopularMoviesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView gridView = findViewById(R.id.gridview);
        gridView.setAdapter(new MovieDisplayAdapter(this, MainActivity.popularMoviesList));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(PopularMoviesActivity.this, "" + "popularity number:" + position, Toast.LENGTH_SHORT).show();
                Intent startMovieDetails = new Intent(PopularMoviesActivity.this, MovieDetails.class);
                Movie movieIncurrentClickedPosition = MainActivity.popularMoviesList.get(position);
                startMovieDetails.putExtra(MainActivity.MOVIE_IN_CURRENT_CLICKED_POSITION, movieIncurrentClickedPosition);
                startActivity(startMovieDetails);
            }
        });




    }


}
