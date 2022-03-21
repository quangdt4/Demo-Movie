package com.example.demo_movie.view.fragment;

import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.demo_movie.R;
import com.example.demo_movie.view.viewmodel.M06RegisterViewModel;

public class M06Register extends BaseFragment<M06RegisterViewModel> {
    private static final String URL = "https://www.themoviedb.org/signup?language=vi";
    public static final String KEY_SHOW_LOGIN = "KEY_SHOW_LOGIN_FROM06";

    @Override
    protected void initViews() {
        ImageView ivBack = findViewById(R.id.iv_back);
        ivBack.setOnClickListener(this);
        WebView register = findViewById(R.id.wv_register);
        register.loadUrl(URL);
    }

    @Override
    protected Class<M06RegisterViewModel> getClassViewModel() {
        return M06RegisterViewModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m006_register;
    }

    @Override
    protected void clickView(View v, int id) {
        if (v.getId() == R.id.iv_back) {
            callBack.onCallBack(KEY_SHOW_LOGIN, false);
        }
    }
}
