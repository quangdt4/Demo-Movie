package com.example.demo_movie.view.fragment;

import android.media.Image;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo_movie.App;
import com.example.demo_movie.R;
import com.example.demo_movie.model.FilmResModel;
import com.example.demo_movie.view.adapter.FilmAdapter;
import com.example.demo_movie.view.viewmodel.M05SearchModel;


public class M05SearchFrg extends BaseFragment<M05SearchModel> implements FilmAdapter.OnItemClick {
    private static final String TAG = M05SearchModel.class.getName();
    public static final String KEY_BACK_LIST = "KEY_BACK";
    public static final String KEY_SHOW_DETAIL = "KEY_SHOW_DETAIL_SEARCH";
    private RecyclerView rvFilm;
    private EditText edtSearch;

    @Override
    protected void initViews() {
        edtSearch = findViewById(R.id.edtSearchMovie);
        ImageView ivSearch = findViewById(R.id.ivSearch);
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        ivSearch.setOnClickListener(this);
        rvFilm = findViewById(R.id.rvSearchMovie);
        rvFilm.setLayoutManager(new LinearLayoutManager(App.getInstance()));
    }

    @Override
    protected Class<M05SearchModel> getClassViewModel() {
        return M05SearchModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m005_search;
    }

    @Override
    protected void clickView(View v, int id) {
        if (v.getId() == R.id.iv_back) {
            callBack.onCallBack(KEY_BACK_LIST, null);
        } else if (v.getId() == R.id.ivSearch) {
            mModel.getListFilmWithSearch(edtSearch.getText().toString());
        }
    }

    @Override
    public void onCallBack(String key, Object data) {
        if (key.equals(M05SearchModel.KEY_NOTIFY)) {
            notify((String) data);
        } else if (key.equals(M05SearchModel.API_KEY_MOVIE)) {
            FilmResModel filmModel = (FilmResModel) data;
            Log.i(TAG, "list" + filmModel.getListResult().size());
            FilmAdapter filmAdapter = new FilmAdapter(filmModel.getListResult(), mContext);
            filmAdapter.setmCallback(this);
            rvFilm.setAdapter(filmAdapter);
        }
    }


    @Override
    public void onItemClick(FilmResModel.Result film, int position) {
        callBack.onCallBack(KEY_SHOW_DETAIL, film);
    }
}
