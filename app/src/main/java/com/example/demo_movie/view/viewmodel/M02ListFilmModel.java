package com.example.demo_movie.view.viewmodel;

import android.util.Log;

import com.example.demo_movie.API;
import com.example.demo_movie.model.FilmResModel;

public class M02ListFilmModel extends BaseViewModel {
    private static final String TAG = M02ListFilmModel.class.getName();
    private static final String API_KEY_GET_REQ_TOKEN = "API_KEY_GET_REQ_TOKEN";
    public static final String API_KEY_MOVIE = "API_KEY_MOVIE";
    private FilmResModel filmModel;

    public void getListFilm() {
        //call API
        API apis = getWS().create(API.class);
        apis.getFilmReq().enqueue(initHandlerRes(API_KEY_MOVIE));
    }

    @Override
    protected <T> void handleSuccess(String key, T body) {
        Log.i(TAG, " handle success: key" + key);
        Log.i(TAG, " handle success: body" + body);
        filmModel = (FilmResModel) body;
        callBack.onCallBack(API_KEY_MOVIE, filmModel);
    }

    @Override
    protected void handleFailed(String key, Object errorBody) {
        Log.i(TAG, " handle failed: key" + key);
        Log.i(TAG, " handle failed: error" + errorBody);
    }
}
