package com.example.demo_movie.view.fragment;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;

import com.example.demo_movie.R;
import com.example.demo_movie.model.DetailFilmResModel;
import com.example.demo_movie.model.FilmResModel;
import com.example.demo_movie.view.viewmodel.M004TrailerFilmModel;


public class M04YoutubeFrg extends BaseFragment<M004TrailerFilmModel> {
    private FilmResModel.Result film;


    private static final String TAG = M04YoutubeFrg.class.getName();

    public void setFilm(FilmResModel.Result film) {
        this.film = film;
    }

    @Override
    protected void initViews() {
        mModel.getTrailer(film.getId());
    }

    @Override
    public void onCallBack(String key, Object data) {
        if (key.equals(M004TrailerFilmModel.KEY_NOTIFY)){
            notify((String) data);
        }else if (key.equals(M004TrailerFilmModel.API_KEY_TRAILER)){
            DetailFilmResModel trailerFilmModel = (DetailFilmResModel) data;

            Log.i(TAG, "onCallBack: TRAILER YOUTUBE VIDEO..... " +  "     " +  "https://www.youtube.com/watch?v=" + trailerFilmModel.getListResult().get(0).getKey());

            watchYoutubeVideo(trailerFilmModel.getListResult().get(0).getKey());

        }
    }
    public void watchYoutubeVideo(String id) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + id));
        Intent webIntent = new Intent(Intent.ACTION_VIEW,
                Uri.parse("http://www.youtube.com/watch?v=" + id));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            startActivity(webIntent);
        }
    }

    @Override
    protected Class<M004TrailerFilmModel> getClassViewModel() {
        return M004TrailerFilmModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m004_trailer;
    }


}
