package com.example.guessit.guessit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ChooseFaction extends AppCompatActivity {

    PlayerDBHandler pdb = new PlayerDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_faction);
    }

    public void monsterFaction(View view) {
        Intent intent = new Intent(this, MonsterActivity.class);
        startActivity(intent);
    }

    public void humanFaction(View view) {
        Intent intent = new Intent(this, HumanActivity.class);
        startActivity(intent);
    }

    // count gamecode number in database



}
