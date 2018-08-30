package com.ikanoid.android.elkrisbot;

import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import javax.xml.transform.Result;

import ai.api.AIListener;
import ai.api.android.AIConfiguration;
import ai.api.android.AIService;
import ai.api.android.GsonFactory;
import ai.api.model.AIError;
import ai.api.model.AIResponse;
//import ai.api.model.Result;
import ai.api.model.Metadata;
import ai.api.model.Status;
import ai.api.ui.AIDialog;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.airbnb.lottie.LottieAnimationView;
import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;
import com.google.gson.Gson;
import com.google.gson.JsonElement;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends BaseActivity implements AIDialog.AIDialogListener {
    private static final String TAG = MainActivity.class.getName();

    private AIDialog aiDialog;
    @BindView(R.id.textView) TextView txt;
    @BindView(R.id.userTextView) TextView userTxt;
    @BindView(R.id.micBtn) Button micButton;
    @BindView(R.id.responseLbl) TextView responseTxt;
    @BindView(R.id.userRequestLbl) TextView userRequestTxt;
    LinearLayout introMessageLayout;
    LottieAnimationView animation_view;

    private Gson gson = GsonFactory.getGson();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Making notification bar transparent
        if (Build.VERSION.SDK_INT >= 21) {
            getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }

        TTS.init(getApplicationContext());

        final AIConfiguration config = new AIConfiguration(Config.ACCESS_TOKEN,
                AIConfiguration.SupportedLanguages.English,
                AIConfiguration.RecognitionEngine.System);
        aiDialog = new AIDialog(this, config);
        aiDialog.setResultsListener(this);

        ButterKnife.bind(this);
        introMessageLayout = (LinearLayout)findViewById(R.id.introMessageLayout);
        animation_view = (LottieAnimationView)findViewById(R.id.animation_view);

        userTxt.setVisibility(View.INVISIBLE);
        txt.setVisibility(View.INVISIBLE);
        responseTxt.setVisibility(View.INVISIBLE);
        userRequestTxt.setVisibility(View.INVISIBLE);

    }

    @OnClick(R.id.micBtn)
    public void onListen(View view){
        introMessageLayout.setVisibility(View.INVISIBLE);
        animation_view.setVisibility(View.INVISIBLE);
        YoYo.with(Techniques.Tada)
                .duration(700)
                .playOn(findViewById(R.id.micBtn));
        aiDialog.showAndListen();
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkAudioRecordPermission();
    }


    @Override
    public void onResult(final AIResponse response) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {

                final ai.api.model.Result result = response.getResult();
                final String speech = result.getFulfillment().getSpeech();
                userTxt.setVisibility(View.VISIBLE);
                userRequestTxt.setVisibility(View.VISIBLE);

                userTxt.setText(result.getResolvedQuery());

                txt.setVisibility(View.VISIBLE);
                responseTxt.setVisibility(View.VISIBLE);
                txt.setText(speech);
                TTS.speak(speech);

            }

        });
    }

    @Override
    public void onError(final AIError error) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt.setText(error.toString());
            }
        });
    }

    @Override
    public void onCancelled() {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                txt.setText("");
            }
        });
    }

    public void aboutBtn(View view) {
        YoYo.with(Techniques.Wobble)
                .duration(1000)
                .playOn(findViewById(R.id.aboutUsBtn));
        Intent intent = new Intent(this, SuccessStoryActivity.class);
        startActivity(intent);
    }
}
