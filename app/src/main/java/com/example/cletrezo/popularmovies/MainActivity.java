package com.example.cletrezo.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;


import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    public static final String MOVIE_IMAGE = "movieImage";
    public static final String MOVIE_TITLE = "movieTitle";// Key for clicked image position;
    public static final String MOVIE_RELEASE_DATE = "movieReleaseDate";// Key for clicked image position;
    public static final String MOVIE_RATING = "movieRating";// Key for clicked image position;
    public static final String MOVIE_DESCRIPTION = "movieDescription";// Key for clicked image position;
    public static final int REQUEST_CODE_POPULAR = 1;
    public static final int REQUEST_CODE_TOP_RATED = 2;
    String topRatedMovieUrl = "https://api.themoviedb.org/3/movie/top_rated?api_key=";
    String popularMoviesUrl = "https://api.themoviedb.org/3/movie/popular?api_key=";
    int counter = 0;
    private ArrayList<Movie> popularMoviesList = new ArrayList<>();
    private ArrayList<Movie> topRatedMoviesList = new ArrayList<>();
    private ArrayList<Movie> movies = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
         final GridView gridView = findViewById(R.id.gridview);
        MovieDataSource object = new MovieDataSource();

        // I got help with the below method from stackoverflow a question i asked
        //https://stackoverflow.com/questions/50553815/using-volley-to-make-2-separate-remote-requests-with-one-method
        OnResponseListener<ArrayList<Movie>> listener = new OnResponseListener<ArrayList<Movie>>() {
            @Override
            public void onSuccess(int tag, ArrayList<Movie> object) {
                if (tag == REQUEST_CODE_POPULAR) {
                    popularMoviesList = object; // initialize popularmovieslist
                    movies.addAll(object); //Add movies to movies
                    Log.i("number of movies added:", String.valueOf(popularMoviesList.size()));
                    counter++; // tracking control/number of times onSuccess in called

                } else if (tag == REQUEST_CODE_TOP_RATED) {
                    topRatedMoviesList = object;
                    movies.addAll(object);
                    Log.i("number of movied added:", String.valueOf(topRatedMoviesList.size()));
                    counter++; // tracking Onsuccess call by Listener
                }

                Collections.shuffle(movies); // popular + top rated movies----shuffle them. This is default display to the user when they first launch the app
                Log.i("counter=", String.valueOf(counter));
                if (counter == 2) { // Do not call the adapter and pass movies for display until background process finishes and movie contains both pop and toprated

                    gridView.setAdapter(new MovieDisplayAdapter(MainActivity.this, movies));
                    Log.i("num of movies passed:", String.valueOf(movies.size()));
                    gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            //Toast.makeText(MainActivity.this, "" + "Rated number:" + position, LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                            //intent.putExtra(IMAGE_POSITION,position);// when image is clicked, the position of the image is recorded and mapped to image_position
                            intent.putExtra(MOVIE_IMAGE, movies.get(position).getMovieImagePath());
                            intent.putExtra(MOVIE_TITLE, movies.get(position).getMovieTitle());
                            intent.putExtra(MOVIE_RELEASE_DATE, movies.get(position).getMovieReleaseDate());
                            intent.putExtra(MOVIE_RATING, movies.get(position).getMovieRating());
                            intent.putExtra(MOVIE_DESCRIPTION, movies.get(position).getMovieDescripton());

                            startActivity(intent);

                        }
                    });
                }

            }

            @Override
            public void onError(Exception e) {
//            todo handle error
            }


        };
        object.movieArrayList(popularMoviesUrl, REQUEST_CODE_POPULAR, listener);
        object.movieArrayList(topRatedMovieUrl, REQUEST_CODE_TOP_RATED, listener);
        Log.i(" of movies passed:", String.valueOf(movies.size()));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.popular) {
            GridView gridView = findViewById(R.id.gridview);
            getSupportActionBar().setTitle("Most Popular Movies");
            gridView.setAdapter(new MovieDisplayAdapter(this, popularMoviesList));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(MainActivity.this, "" + "popularity, number:" + position, LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                    //intent.putExtra(IMAGE_POSITION,position);// when image is clicked, the position of the image is recorded and mapped to image_position
                    intent.putExtra(MOVIE_IMAGE, popularMoviesList.get(position).getMovieImagePath());
                    intent.putExtra(MOVIE_TITLE, popularMoviesList.get(position).getMovieTitle());
                    intent.putExtra(MOVIE_RELEASE_DATE, popularMoviesList.get(position).getMovieReleaseDate());
                    intent.putExtra(MOVIE_RATING, popularMoviesList.get(position).getMovieRating());
                    intent.putExtra(MOVIE_DESCRIPTION, popularMoviesList.get(position).getMovieDescripton());

                    startActivity(intent);

                }
            });

        } else if (item.getItemId() == R.id.toprated) {
            GridView gridView = findViewById(R.id.gridview);
            getSupportActionBar().setTitle("Top Rated Movies");
            gridView.setAdapter(new MovieDisplayAdapter(this, topRatedMoviesList));
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Toast.makeText(MainActivity.this, "" + "Rated number:" + position, LENGTH_SHORT).show();

                    Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                    //intent.putExtra(IMAGE_POSITION,position);// when image is clicked, the position of the image is recorded and mapped to image_position
                    intent.putExtra(MOVIE_IMAGE, topRatedMoviesList.get(position).getMovieImagePath());
                    intent.putExtra(MOVIE_TITLE, topRatedMoviesList.get(position).getMovieTitle());
                    intent.putExtra(MOVIE_RELEASE_DATE, topRatedMoviesList.get(position).getMovieReleaseDate());
                    intent.putExtra(MOVIE_RATING, topRatedMoviesList.get(position).getMovieRating());
                    intent.putExtra(MOVIE_DESCRIPTION, topRatedMoviesList.get(position).getMovieDescripton());

                    startActivity(intent);

                }
            });

        } else {


            return super.onOptionsItemSelected(item);
        }
        return true;
    }
}



