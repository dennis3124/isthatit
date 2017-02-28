package com.example.guessit.guessit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import static com.example.guessit.guessit.HumanActivity.lastAvatar;
import static com.example.guessit.guessit.R.id.imageView;

public class Waiting extends AppCompatActivity {
    ImageButton button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //imageView.setImageResource(lastAvatar);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_waiting);
    }

    public void goToGame(View view) {
        startActivity(new Intent(this, GamePage.class));
    }
}
