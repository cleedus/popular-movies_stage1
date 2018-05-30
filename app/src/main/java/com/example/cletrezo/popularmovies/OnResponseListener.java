package com.example.cletrezo.popularmovies;

import java.util.ArrayList;
import java.util.List;

public interface OnResponseListener<T extends List> {
    public void onSuccess(int tag, T object);
    void onError( Exception e);
}
