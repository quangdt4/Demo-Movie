package com.example.demo_movie.view.viewmodel;

import android.util.Log;

import com.example.demo_movie.API;
import com.example.demo_movie.model.DetailFilmResModel;


public class M03DetailViewModel extends BaseViewModel {
    private static final String TAG = M03DetailViewModel.class.getName();
    public static final String API_KEY_DETAIL = "API_KEY_DETAIL";
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    public void getFilmVideo() {
        //call API
        API apis = getWS().create(API.class);
        apis.getFilmVideoReq(id).enqueue(initHandlerRes(API_KEY_DETAIL));
    }

    @Override
    protected <T> void handleSuccess(String key, T body) {
        Log.i(TAG, " handle success: key" + key);
        Log.i(TAG, " handle success: body" + body);
        DetailFilmResModel detailFilm = (DetailFilmResModel) body;
        callBack.onCallBack(API_KEY_DETAIL, detailFilm);
    }

    @Override
    protected void handleFailed(String key, Object errorBody) {
        Log.i(TAG, " handle failed: key" + key);
        Log.i(TAG, " handle failed: error" + errorBody);
    }
}
