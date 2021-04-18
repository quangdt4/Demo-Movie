package com.example.demo_movie.view.fragment;

import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.example.demo_movie.App;
import com.example.demo_movie.R;
import com.example.demo_movie.view.viewmodel.BaseViewModel;
import com.example.demo_movie.view.viewmodel.M01LoginModel;

public class M01LoginFrg extends BaseFragment<M01LoginModel> {
    public static final String KEY_SHOW_LIST_FILM = "KEY_SHOW_LIST_FILM";
    private EditText edtUsername, edtPass;
    private CheckBox checkBox;

    @Override
    protected void initViews() {
        edtUsername = findViewById(R.id.edt_user_name);
        edtPass = findViewById(R.id.edt_pass);
        findViewById(R.id.tv_login).setOnClickListener(this);
        checkBox = findViewById(R.id.cb_remember);
    }

    @Override
    protected Class<M01LoginModel> getClassViewModel() {
        return M01LoginModel.class;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.frg_m001_login;
    }

    @Override
    public void clickView(View v, int id) {
        if (v.getId() == R.id.tv_login && checkBox.isChecked()) {
            doLogin(edtUsername.getText().toString(), edtPass.getText().toString(), 1);
        } else if (v.getId() == R.id.tv_login && !checkBox.isChecked()) {
            doLogin(edtUsername.getText().toString(), edtPass.getText().toString(), 0);
            Toast.makeText(App.getInstance(), "Unchecked", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onCallBack(String key, Object data) {
        if (key.equals(BaseViewModel.KEY_NOTIFY)) {
            notify(data.toString());
        } else if (key.equals(M01LoginModel.API_KEY_LOGIN)) {
            doAfterLogin();
        }
    }

    private void doLogin(String userName, String pass, int check) {
        if (userName.isEmpty() || pass.isEmpty()) {
            notify("Please input values");
            return;
        }
        mModel.setCheck(check);
        mModel.login(userName, pass);

    }

    private void doAfterLogin() {
        notify("Login success");
        callBack.onCallBack(KEY_SHOW_LIST_FILM, null);
    }
}
