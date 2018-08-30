package com.ikanoid.android.elkrisbot;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

/**
 * Created by ROBO on 8/29/2018.
 */

public class SuccessStoryActivity extends YouTubeBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_about_us);

        YouTubePlayerView youTubePlayerView =
                (YouTubePlayerView) findViewById(R.id.videoView);

        youTubePlayerView.initialize("Youtube api key",
                new YouTubePlayer.OnInitializedListener() {
                    @Override
                    public void onInitializationSuccess(YouTubePlayer.Provider provider,
                                                        YouTubePlayer youTubePlayer, boolean b) {

                        // do any work here to cue video, play video, etc.
                        youTubePlayer.loadVideo("EqYRCU7hTVE");
                    }
                    @Override
                    public void onInitializationFailure(YouTubePlayer.Provider provider,
                                                        YouTubeInitializationResult youTubeInitializationResult) {

                    }
                });
    }

    public void chatbotBtn(View view) {
        YoYo.with(Techniques.Wobble)
                .duration(1000)
                .playOn(findViewById(R.id.chatBotBtn));
        Intent chatbotIntent = new Intent(this, MainActivity.class);
        startActivity(chatbotIntent);
    }
}
