package com.example.demo_movie.view.act;

import android.os.Bundle;
import android.widget.Toast;
import com.example.demo_movie.App;
import com.example.demo_movie.R;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class YoutubeDialog extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {
    private String id;

    public void setId(String id) {
        this.id = id;
    }

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.dialog_youtube_video);
        YouTubePlayerView ytpv = findViewById(R.id.ytpv);
        String API_KEY_YT = "AIzaSyAQAIcmVkW0WoSV6_MbqJmsI_I4uTmd-3o";
        ytpv.initialize(API_KEY_YT, this);
        setId(App.getInstance().getStorage().getId());
    }

    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
        if (!b) {
            youTubePlayer.cueVideo(id);
        }
    }

    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult result) {
        if (result.isUserRecoverableError()) {
            result.getErrorDialog(this, 1).show();
        } else {
            String error = String.format("Error initializing YouTube player", result.toString());
            Toast.makeText(this, error, Toast.LENGTH_LONG).show();
        }
    }
}
