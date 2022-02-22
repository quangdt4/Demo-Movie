package com.example.demo_movie.view.viewmodel;

import android.util.Log;

import com.example.demo_movie.API;
import com.example.demo_movie.model.DetailFilmResModel;


public class M004TrailerFilmModel extends BaseViewModel {
    public static final String API_KEY_TRAILER = "API_KEY_TRAILER";
    private static final String TAG = M004TrailerFilmModel.class.getName();


    public void getTrailer(String movieId) {
        //String filmId = App.getInstance().getId();
        API apis = getWS().create(API.class);
        apis.getFilmVideoReq(movieId).enqueue(initHandlerRes(API_KEY_TRAILER));
    }

    @Override
    protected <T> void handleSuccess(String key, T body) {
        DetailFilmResModel trailerFilmModel = (DetailFilmResModel) body;
        callBack.onCallBack(API_KEY_TRAILER, trailerFilmModel);
        Log.i(TAG, "handleSuccess: " + key);
        Log.i(TAG, "handleSuccess: " + body);
    }

    @Override
    protected void handleFailed(String key, Object errorBody) {
            Log.i(TAG, " handle failed: key" + key);
            Log.i(TAG, " handle failed: error" + errorBody);
    }
}
