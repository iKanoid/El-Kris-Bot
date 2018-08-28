package com.ikanoid.android.elkrisbot;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by Etsiakoh on 10/6/2017.
 */

public class ContinueActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);
    }

    public void onContinue (View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
