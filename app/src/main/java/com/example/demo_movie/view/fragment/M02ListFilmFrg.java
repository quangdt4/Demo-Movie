package com.example.demo_movie.view.fragment;

import android.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_movie.App;
import com.example.demo_movie.CommonUtils;
import com.example.demo_movie.R;
import com.example.demo_movie.model.FilmResModel;
import com.example.demo_movie.view.adapter.FilmAdapter;
import com.example.demo_movie.view.viewmodel.M02ListFilmModel;

public class M02ListFilmFrg extends BaseFragment<M02ListFilmModel> implements FilmAdapter.OnItemClick {
    private static final String TAG = M02ListFilmFrg.class.getName();
    public static final String KEY_SHOW_DETAIL = "KEY_SHOW_DETAIL";
    public static final String KEY_TO_LOGIN = "KEY_TO_LOGIN";
    public static final String KEY_TO_SEARCH = "KEY_TO_SEARCH";
    private RecyclerView rvFilm;

    @Override
    protected void initViews() {
        ImageView ivLogOut = findViewById(R.id.ic_logout);
        ImageView ivSearch = findViewById(R.id.ic_search);
        ivLogOut.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        rvFilm = findViewById(R.id.rv_film);
        rvFilm.setLayoutManager(new LinearLayoutManager(App.getInstance()));
        mModel.getListFilm();
    }

    @Override
    protected void clickView(View v, int id) {
        if (v.getId() == R.id.ic_logout) {
            new AlertDialog.Builder(getContext())
                    .setTitle("Confirm")
                    .setPositiveButton("YES!", (dialog, which) -> {
                        callBack.onCallBack(KEY_TO_LOGIN, null);
                        CommonUtils.getInstance().clearPref("TOKEN_REQ");
                    })
                    .setNegativeButton("CANCEL", (dialog, which) -> dialog.cancel())
                    .setMessage("Do you want log out now?")
                    .show();
        } else if (v.getId() == R.id.ic_search) {
            callBack.onCallBack(KEY_TO_SEARCH, null);
        }
    }

    @Override
    protected Class<M02ListFilmModel> getClassViewModel() {
        return M02ListFilmModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m002_list_film;
    }

    @Override
    public void onCallBack(String key, Object data) {
        if (key.equals(M02ListFilmModel.KEY_NOTIFY)) {
            notify((String) data);
        } else if (key.equals(M02ListFilmModel.API_KEY_MOVIE)) {
            FilmResModel filmModel = (FilmResModel) data;
            Log.i(TAG, "list" + filmModel.getListResult().size());
            FilmAdapter filmAdapter = new FilmAdapter(filmModel.getListResult(), mContext);
            filmAdapter.setmCallback(this);
            rvFilm.setAdapter(filmAdapter);
        }
    }

    @Override
    public void onItemClick(FilmResModel.Result film, int pos) {
        callBack.onCallBack(KEY_SHOW_DETAIL, film);
    }
}
