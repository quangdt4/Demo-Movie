package com.example.demo_movie.view.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.demo_movie.App;
import com.example.demo_movie.R;
import com.example.demo_movie.model.DetailFilmResModel;
import com.example.demo_movie.model.FilmResModel;
import com.example.demo_movie.view.act.YoutubeDialog;
import com.example.demo_movie.view.viewmodel.M03DetailViewModel;

public class M03DetailFilmFrg extends BaseFragment<M03DetailViewModel> {
    public static final String KEY_BACK_LIST = "KEY_BACK_LIST";
    public static final String KEY_SHOW_TRAILER = "KEY_SHOW_TRAILER";
    private static final String TAG = M03DetailViewModel.class.getName();
    private FilmResModel.Result film;
    private DetailFilmResModel.Result detailRs;
    private ImageView detailPoster;
    private ImageView detailDropback;
    private TextView tvAve;
    private TextView tvTitle;
    private TextView tvOverview;
    private TextView tvOriginalTitle;
    private TextView tvRelease;
    private TextView tvLanguage;
    private TextView tvVote;

    public void setFilm(FilmResModel.Result film) {
        this.film = film;
    }

    @Override
    protected void initViews() {
        detailPoster = findViewById(R.id.iv_detail_poster);
        detailDropback = findViewById(R.id.iv_backdrop);
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        TableRow tvPlay = findViewById(R.id.tv_play_trailer);
        tvPlay.setOnClickListener(this);
        tvTitle = findViewById(R.id.tv_detail_title);
        tvAve = findViewById(R.id.tv_average);
        tvOverview = findViewById(R.id.tv_detail_overview);
        tvOriginalTitle = findViewById(R.id.tv_detail_original_title);
        tvRelease = findViewById(R.id.tv_detail_release);
        tvLanguage = findViewById(R.id.tv_detail_language);
        tvVote = findViewById(R.id.tv_detail_vote_count);
        findViewById(R.id.iv_back);

        mModel.setId(film.getId());
        mModel.getFilmVideo();
        initData();
    }

    @SuppressLint("SetTextI18n")
    private void initData() {
        mModel.setId(film.getId());

        Glide.with(mContext).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + film.getPosterPath())
                .into(detailPoster);
        Glide.with(mContext).load("https://www.themoviedb.org/t/p/w600_and_h900_bestv2/" + film.getDropback())
                .into(detailDropback);
        tvTitle.setText(film.getTitle());
        tvOverview.setText(film.getOverview());
        tvOriginalTitle.setText(film.getOriginaleTitle());
        tvRelease.setText(film.getTime());
        tvVote.setText(film.getVoteCount());
        double ave = Double.parseDouble(film.getVote());
        if (ave >= 8) {
            tvAve.setTextColor(Color.parseColor("#4FAC53"));
        } else {
            tvAve.setTextColor(Color.parseColor("#FDD835"));
        }
        tvAve.setText(film.getVote());

        switch (film.getLanguage()) {
            case "en":
                tvLanguage.setText("English");
                break;
            case "ko":
                tvLanguage.setText("Korean");
                break;
            case "es":
                tvLanguage.setText("Spanish");
                break;
            case "fr":
                tvLanguage.setText("French");
                break;
            case "sv":
                tvLanguage.setText("Swedish");
                break;
        }
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    protected void clickView(View v, int id) {
        switch (v.getId()) {
            case R.id.iv_back:
                callBack.onCallBack(KEY_BACK_LIST, null);
                break;
            case R.id.tv_play_trailer:
                showTrailer();
                break;
        }
    }

    @Override
    protected Class<M03DetailViewModel> getClassViewModel() {
        return M03DetailViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m003_detail;
    }

    @Override
    public void onCallBack(String key, Object data) {
        if (key.equals(M03DetailViewModel.KEY_NOTIFY)) {
            notify((String) data);
        } else if (key.equals(M03DetailViewModel.API_KEY_DETAIL)) {
            DetailFilmResModel filmModel = (DetailFilmResModel) data;
            detailRs = filmModel.getListResult().get(0);
            Log.i(TAG, " key film: " + detailRs.getKey());
        }
    }

    private void showTrailer() {
//        App.getInstance().getStorage().setId(detailRs.getKey());
//        Intent intent = new Intent();
//        intent.setClass(App.getInstance(), YoutubeDialog.class);
//        startActivity(intent);
        callBack.onCallBack(KEY_SHOW_TRAILER, film);
    }
}
