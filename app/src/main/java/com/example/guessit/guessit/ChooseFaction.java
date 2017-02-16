package com.example.guessit.guessit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ChooseFaction extends AppCompatActivity {
    private int[] icons = {
            R.drawable.monster1,
            R.drawable.monster2,
            R.drawable.monster3,
            R.drawable.monster4,
            R.drawable.monster5,
            R.drawable.monster6,
            R.drawable.monster7,
            R.drawable.monster8,
            R.drawable.monster9,
    };
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
}
