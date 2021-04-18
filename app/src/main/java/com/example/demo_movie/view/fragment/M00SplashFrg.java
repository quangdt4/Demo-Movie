package com.example.demo_movie.view.fragment;

import android.os.Handler;

import com.example.demo_movie.CommonUtils;
import com.example.demo_movie.R;
import com.example.demo_movie.view.viewmodel.M01LoginModel;

public class M00SplashFrg extends BaseFragment<M01LoginModel> {
    public static final String KEY_SHOW_LOGIN = "KEY_SHOW_LOGIN";
    public static final String KEY_SHOW_LIST = "KEY_SHOW_LIST";

    @Override
    protected void initViews() {
        checkExistPref();
        //new Handler().postDelayed(this::goToLoginFrg, 1000);
    }

    private void checkExistPref() {
        if (CommonUtils.getInstance().isExistPref("TOKEN_REQ")) {
            new Handler().postDelayed(this::goToListFrg, 1000);
        } else {
            new Handler().postDelayed(this::goToLoginFrg, 1000);
        }
    }

    private void goToListFrg() {
        callBack.onCallBack(KEY_SHOW_LIST, null);
    }

    private void goToLoginFrg() {
        callBack.onCallBack(KEY_SHOW_LOGIN, null);
    }

    @Override
    protected Class<M01LoginModel> getClassViewModel() {
        return M01LoginModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m000_splash;
    }
}
