package com.example.demo_movie.view.act;

import com.example.demo_movie.OnActionCallBack;
import com.example.demo_movie.R;
import com.example.demo_movie.model.FilmResModel;
import com.example.demo_movie.view.fragment.M00SplashFrg;
import com.example.demo_movie.view.fragment.M01LoginFrg;
import com.example.demo_movie.view.fragment.M02ListFilmFrg;
import com.example.demo_movie.view.fragment.M03DetailFilmFrg;
import com.example.demo_movie.view.fragment.M04YoutubeFrg;
import com.example.demo_movie.view.fragment.M05SearchFrg;
import com.example.demo_movie.view.viewmodel.M02ListFilmModel;

public class MainActivity extends BaseAct<M02ListFilmModel> implements OnActionCallBack {

    @Override
    protected Class<M02ListFilmModel> getClassViewModel() {
        return M02ListFilmModel.class;
    }

    @Override
    protected void initViews() {
        M00SplashFrg splash = new M00SplashFrg();
        splash.setCallBack(this);
        showFragment(R.id.container_view, splash, false);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public void onCallBack(String key, Object data) {
        switch (key) {
            case M00SplashFrg.KEY_SHOW_LOGIN:
            case M02ListFilmFrg.KEY_TO_LOGIN:
                M01LoginFrg loginFrg = new M01LoginFrg();
                loginFrg.setCallBack(this);
                showFragment(R.id.container_view, loginFrg, false);
                break;
            case M00SplashFrg.KEY_SHOW_LIST:
            case M01LoginFrg.KEY_SHOW_LIST_FILM:
            case M05SearchFrg.KEY_BACK_LIST:
            case M03DetailFilmFrg.KEY_BACK_LIST:
                M02ListFilmFrg listFilm = new M02ListFilmFrg();
                listFilm.setCallBack(this);
                showFragment(R.id.container_view, listFilm, false);
                break;
            case M02ListFilmFrg.KEY_SHOW_DETAIL:
            case M05SearchFrg.KEY_SHOW_DETAIL:
                M03DetailFilmFrg detailFilm = new M03DetailFilmFrg();
                FilmResModel.Result film = (FilmResModel.Result) data;
                detailFilm.setFilm(film);
                detailFilm.setCallBack(this);
                showFragment(R.id.container_view, detailFilm, false);
                break;
            case M02ListFilmFrg.KEY_TO_SEARCH:
                M05SearchFrg searchFrg = new M05SearchFrg();
                searchFrg.setCallBack(this);
                showFragment(R.id.container_view, searchFrg, false);
                break;
            case M03DetailFilmFrg.KEY_SHOW_TRAILER:
                M04YoutubeFrg m004Frg = new M04YoutubeFrg();
                FilmResModel.Result film1 = (FilmResModel.Result) data;
                m004Frg.setFilm(film1);
                m004Frg.setCallBack(this);
                showFragment(R.id.container_view, m004Frg, true);
                break;
        }
    }
}