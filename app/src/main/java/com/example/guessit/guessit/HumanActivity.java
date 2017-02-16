package com.example.guessit.guessit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class HumanActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_human);
    }

    public void goToGamePage(View view) {
        Intent intent = new Intent(this, GamePage.class);
        startActivity(intent);
    }

    public void goToWaitingPg(View view) {
        Log.d("Testing id", Integer.toString(view.getId()));
        Intent intent = new Intent(this, HintActivity.class);
        startActivity(intent);
    }
}
