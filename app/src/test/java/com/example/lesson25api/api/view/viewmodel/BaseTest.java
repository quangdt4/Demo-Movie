package com.example.lesson25api.api.view.viewmodel;

import com.example.demo_movie.App;
import com.example.demo_movie.CommonUtils;
import com.example.demo_movie.OnActionCallBack;
import com.example.demo_movie.view.viewmodel.BaseViewModel;
import com.example.demo_movie.view.viewmodel.M01LoginModel;

import junit.framework.TestCase;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import java.io.InputStream;

import okhttp3.mockwebserver.MockWebServer;

@RunWith(RobolectricTestRunner.class)
@Config(application = App.class, sdk = 23)
public abstract class BaseTest<T extends BaseViewModel> extends TestCase implements OnActionCallBack {
    protected T mModel;
    protected MockWebServer webServer;

    @Before
    public void setup() {
        CommonUtils.logLevel = CommonUtils.LEVEL_DEBUG;

        MockitoAnnotations.openMocks(this);
        mModel = Mockito.spy(initModelClass());
        mModel.setCallBack(this);
        webServer = new MockWebServer();

        M01LoginModel.BASE_URL = webServer.url("/").toString();
        System.out.println("URL: " + M01LoginModel.BASE_URL);
    }

    protected abstract Class<T> initModelClass();

    @Override
    public void onCallBack(String key, Object data) {
        if (key.equals(M01LoginModel.KEY_NOTIFY) && data != null) {
            assertTrue(true);
            System.out.println("==>Testcase fail success!");
        }
    }

    @After
    public void tealDown() {
        //webServer = null;
        //mModel = null;
    }


    protected final String getText(String fileName) {
        try {
            InputStream in = getClass().getResourceAsStream("/api/" + fileName);
            if (in == null) return null;

            StringBuilder text = new StringBuilder();
            byte[] buff = new byte[1024];
            int len = in.read(buff);
            while (len > 0) {
                text.append(new String(buff, 0, len));
                len = in.read(buff);
            }
            in.close();
            return text.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
