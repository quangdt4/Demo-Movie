package com.example.lesson25api.api.view.viewmodel;

import com.example.demo_movie.App;
import com.example.demo_movie.view.viewmodel.BaseViewModel;
import com.example.demo_movie.view.viewmodel.M01LoginModel;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.util.Objects;

import okhttp3.mockwebserver.MockResponse;


@RunWith(RobolectricTestRunner.class)
@Config(application = App.class, sdk = 23)
public class M01LoginModelTest extends BaseTest<M01LoginModel> {

    @Override
    protected Class<M01LoginModel> initModelClass() {
        return M01LoginModel.class;
    }

    @Test
    public void testLoginSuccess() {
        webServer.enqueue(new MockResponse() //enqueue: cho vao hang` cho`
                .setBody(Objects.requireNonNull(getText("GetAuthenSuccess.json")))
                .setResponseCode(200));

        webServer.enqueue(new MockResponse()
                .setBody(Objects.requireNonNull(getText("CreateSessionWithLogin.json")))
                .setResponseCode(200));

        //test login success
        mModel.login("NeosThanh", "Anhpham2m");
    }

    @Test
    public void testLoginFail() {
        webServer.enqueue(new MockResponse() //enqueue: cho vao hang` cho`
                .setBody(Objects.requireNonNull(getText("CreateSessionWithLoginFail.json")))
                .setResponseCode(401));

        mModel.login("Neos123", "Anhpham2m");
    }

    @Override
    public void onCallBack(String key, Object data) {
        if (key.equals(M01LoginModel.API_KEY_LOGIN) && data != null) {
            assertTrue(true);
            System.out.println("TestSuccess -> Pass");
        } else if (key.equals(BaseViewModel.KEY_NOTIFY) && data != null) {
            assertTrue(true);
            System.out.println("TestFail -> Pass");
        }
    }
}