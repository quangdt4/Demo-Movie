package com.example.demo_movie;

import android.app.Application;

//SOURCE
public class App extends Application {
    private static App instance;

    private Storage storage;

    public static App getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        storage = new Storage();
    }

    public Storage getStorage() {
        return storage;
    }
}
