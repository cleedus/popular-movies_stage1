package com.example.cletrezo.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.widget.Toast.LENGTH_SHORT;

public class MainActivity extends AppCompatActivity {
    public static final String Image_position = "PositionOfImage"; // Key for clicked image position;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GridView gridView = findViewById(R.id.gridview);
        gridView.setAdapter(new MovieDisplayAdapter(this));
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(MainActivity.this, ""+ "You clicked picture:"+position+ "movieid is:"+ id, LENGTH_SHORT).show();

                Intent intent = new Intent(MainActivity.this, MovieDetails.class);
                intent.putExtra(Image_position,position);// when image is clicked, the position of the image is recorded and mapped to image_position
                startActivity(intent);

            }
        });

    }
}
