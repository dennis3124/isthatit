package com.example.guessit.guessit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class StartGameUsername extends AppCompatActivity {
    EditText username;
    Button gotofaction;
    static int globalplayercount = 0; // increment to 1, 2
    static int globalgameid = 100; // increment to 101, 102 etc. (prevent unique error)
    Players player;
    Games game;
    PlayerDBHandler pdb = new PlayerDBHandler(this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game_username);
        username = (EditText)findViewById(R.id.usernamefield);
        gotofaction = (Button)findViewById(R.id.gotofaction);
        gotofaction.setEnabled(false);
        player = new Players(globalplayercount, 0, "", "", globalgameid, "");
        game = new Games(globalgameid);
    }

    public void gotoFaction(View view) {
        startActivity(new Intent(this, ChooseFaction.class));
    }

    public void checkusername(View view) {
        // Check if shorter than 1
        if (username.getText().toString().length() < 1) {
            Toast.makeText(getApplicationContext(), "Username too short", Toast.LENGTH_LONG).show();
            gotofaction.setEnabled(false);
        } else if (username.getText().toString().length() > 25) { // Check if longer than 25
            Toast.makeText(getApplicationContext(), "Username too long", Toast.LENGTH_LONG).show();
            gotofaction.setEnabled(false);
        } else if (!username.getText().toString().matches("[a-zA-Z0-9]*")) { // Check if alphanumeric
            Toast.makeText(getApplicationContext(), "Username must only be alphanumeric with no space", Toast.LENGTH_SHORT).show();
            gotofaction.setEnabled(false);
        } else {
            Toast.makeText(getApplicationContext(), "Awesome username!", Toast.LENGTH_LONG).show();
            player.setUserName(username.getText().toString());
            pdb.addPlayer(player);
            // Added player, so increment count
            globalplayercount++;
            gotofaction.setEnabled(true);

        }
        // Check if in database table already
    }
}
