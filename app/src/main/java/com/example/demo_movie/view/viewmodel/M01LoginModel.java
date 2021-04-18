package com.example.demo_movie.view.viewmodel;

import android.widget.Toast;

import com.example.demo_movie.API;
import com.example.demo_movie.App;
import com.example.demo_movie.CommonUtils;
import com.example.demo_movie.model.AccountReqModel;
import com.example.demo_movie.model.TokenResModel;


public class M01LoginModel extends BaseViewModel {
    private static final String TAG = M01LoginModel.class.getName();
    private static final String API_KEY_GET_REQ_TOKEN = "API_KEY_GET_REQ_TOKEN";
    public static final String API_KEY_LOGIN = "API_KEY_LOGIN";
    private String username, pass;
    private int check = 0;

    public void setCheck(int check) {
        this.check = check;
    }

    public void login(String userName, String pass) {
        this.username = userName;
        this.pass = pass;

        //call API getTokenReq
        API apis = getWS().create(API.class);
        apis.getTokenReq().enqueue(initHandlerRes(API_KEY_GET_REQ_TOKEN));
    }

    @Override
    protected <T> void handleSuccess(String key, T body) {
        CommonUtils.logI(TAG, " handle success: key" + key);
        CommonUtils.logI(TAG, " handle success: body" + body);

        if (key.equals(API_KEY_GET_REQ_TOKEN) && check == 1) {
            saveTokenReq((TokenResModel) body);
            Toast.makeText(App.getInstance(), "Checked", Toast.LENGTH_SHORT).show();
        } else if (key.equals(API_KEY_GET_REQ_TOKEN) && check == 0) {
            doLogin((TokenResModel) body);
            Toast.makeText(App.getInstance(), "UnChecked", Toast.LENGTH_SHORT).show();
        } else if (key.equals(API_KEY_LOGIN)) {
            doAfterLogin((TokenResModel) body);
        }
    }

    @Override
    protected void handleFailed(String key, Object errorBody) {
        CommonUtils.logI(TAG, " handle failed: key" + key);
        CommonUtils.logI(TAG, " handle failed: error" + errorBody);

        if (key.equals(API_KEY_GET_REQ_TOKEN)) {
            callBack.onCallBack(BaseViewModel.KEY_NOTIFY, "Error: Get request token fail");
        } else if (key.equals(API_KEY_LOGIN)) {
            callBack.onCallBack(BaseViewModel.KEY_NOTIFY, "Error: Login failed");
        }
    }

    private void doAfterLogin(TokenResModel body) {
        //call to ui
        callBack.onCallBack(API_KEY_LOGIN, body);
    }

    private void saveTokenReq(TokenResModel body) {
        CommonUtils.getInstance().savePref(BaseViewModel.TOKEN_REQ, body.getRequestToken());
        doLogin(body);
    }

    private void doLogin(TokenResModel body) {
        //call API login
        AccountReqModel acc = new AccountReqModel(username, pass, body.getRequestToken());
        API apis = getWS().create(API.class);
        apis.login(acc).enqueue(initHandlerRes(API_KEY_LOGIN));
    }


}
