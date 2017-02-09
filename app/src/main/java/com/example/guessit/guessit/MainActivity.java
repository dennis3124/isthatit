package com.example.guessit.guessit;
import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.view.View.*;
import android.widget.*;
import android.view.View.OnClickListener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import java.util.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {
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



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button viewScoreTableButton = (Button) findViewById(R.id.viewScoreButton);
        viewScoreTableButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, ScoreTable.class));
            }
        });

        Button viewHintButton = (Button) findViewById(R.id.viewHintButton);
        viewHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(MainActivity.this, HintPage.class));
            }
        });
    }

    public void monsterFaction(View view) {
//        GameDBHandler db = new GameDBHandler(this);
//        PlayerDBHandler pdb = new PlayerDBHandler(this);
//
//        Log.d("Insert: ", "Inserting ..");
//        Players p1 = new Players (0,0,"dennis3124","Monster",0,"MonsterAvatar");
//        String log2 = "PlayerId " + p1.getId() + "\nscore: " + p1.getScore() + "\nUser Name: " +  p1.getUserName() + "\nFaction: " + p1.getFaction() + "\nRoom Number: " + p1.getGameId() + "\nAvatar: " + p1.getAvatar();
//            Log.d("Player: ", log2);
//        pdb.addPlayer(p1);
//
//        //Read all games
////        Log.d("Reading: ", "Reading all Games");
////        List<Games> games = db.getAllGames();
////
////        for(Games game : games) {
////            String log = "Id: " + game.getId() + " Number of Player: " + game.getNumPlayers();
////            Log.d("Games: : ", log);
////        }
//
//        Log.d("Reading: ", "Reading PlayerS");
//        List<Players> players = pdb.getAllPlayers();
//
//        for(Players p2 : players) {
//            String log3 = "PlayerId " + p2.getId() + "score: " + p2.getScore() + "User Name: " +  p2.getUserName() + "Faction: " + p2.getFaction() + "Room Number: " + p2.getGameId() + "Avatar: " + p2.getAvatar();
//            Log.d("Player: ", log3);
//        }
//
//        pdb.deletePlayer(p1);
//
//    }
        Intent intent = new Intent(this, MonsterActivity.class);
        startActivity(intent);
    }

    public void humanFaction(View view) {
        Intent intent = new Intent(this, HumanActivity.class);
        startActivity(intent);
    }

    public void toServer(View view) {
        Intent intent = new Intent(this,ServerActivity.class);
        startActivity(intent);
    }

}
