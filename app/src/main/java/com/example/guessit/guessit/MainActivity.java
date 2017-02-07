package com.example.guessit.guessit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        GameDBHandler db = new GameDBHandler(this);

        //insert a game (Test)
        Log.d("Insert: ", "Inserting ..");
        db.createGame(new Games(1));
        db.createGame(new Games(2));
        db.createGame(new Games(3));

        //Read all games
        Log.d("Reading: ", "Reading all Games");
        List<Games> games = db.getAllGames();

        for(Games game : games) {
            String log = "Id: " + game.getId() + " Number of Player: " + game.getNumPlayers();
            Log.d("Games: : ", log);
        }
    }
}
