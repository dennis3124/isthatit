package com.example.guessit.guessit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class HumanActivity extends AppCompatActivity {
    ImageButton button1;
    static String lastAvatar;
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
        //Log.d("Testing resource id", getResources().getResourceEntryName(view.getId()));
        lastAvatar = getResources().getResourceEntryName(view.getId());
        // Disables user from clicking again after clicking
        view.setEnabled(false);
        // Start activity hint
        Intent intent = new Intent(this, HintActivity.class);
        startActivity(intent);
    }
}
