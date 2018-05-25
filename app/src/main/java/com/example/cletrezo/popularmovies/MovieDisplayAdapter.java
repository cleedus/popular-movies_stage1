package com.example.cletrezo.popularmovies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.cletrezo.popularmovies.Movie;
import com.example.cletrezo.popularmovies.MovieDataSource;
import com.example.cletrezo.popularmovies.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MovieDisplayAdapter extends BaseAdapter {
    private ArrayList<Movie> movies = MovieDataSource.movieArrayList();
    private Context context;

    MovieDisplayAdapter(Context context){
        this.context= context;
    }


    @Override
    public int getCount() {
        return  movies.size();
    }

    @Override
    public Object getItem(int position) {
        return movies.get(position);
    }

    @Override
    public long getItemId(int position) {
        return movies.get(position).getMovieid(); // returning movie id for intent purposes
    }
    class ViewHolder {
        ImageView imageView;

        ViewHolder(View view) {
            imageView = view.findViewById(R.id.imageView);
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {


        ViewHolder holder;
        LayoutInflater inflater = LayoutInflater.from(context);

        if (convertView == null) {

            convertView = inflater.inflate(R.layout.single_row,parent,false);
            holder= new ViewHolder(convertView);
            convertView.setTag(holder);

        }
        else

        {
            holder = (ViewHolder) convertView.getTag();
        }

        Picasso.with(context)
                .load(movies.get(position).getMovieImagePath())
                // .resize(400,400)
                .fit()//Load the image
                .centerCrop()
                //.placeholder() //Image resource that act as placeholder
                //.error(R.drawable.) //Image resource for error
                // .resize(300, 500)  // Post processing - Resizing the image
                .into(holder.imageView); // View where image is loaded.


        return convertView;
    }

}
