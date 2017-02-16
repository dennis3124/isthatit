package com.example.guessit.guessit;
import android.app.Activity;
import android.content.Intent;
import android.os.CountDownTimer;
import android.view.View;
import android.view.View.*;
import android.widget.*;
import android.view.View.OnClickListener;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.concurrent.TimeUnit;
import android.util.Log;
import java.util.List;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import org.w3c.dom.Text;


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

    private CountDownTimer countDownTimer;
    private Button timerStart;
    public TextView timerView;
    private static final String FORMAT = "%02d:%02d";
    private long startTime = 130*1000;
    private long interval = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_start_join_game);

    }

    public void startGame(View view) {
        // Navigate to start game and enter user name page
        startActivity(new Intent(this, StartGameUsername.class));
    }

    public void joinGame(View view) {
        // Navigate to join game and enter user name page
        startActivity(new Intent(this, JoinGameUsername.class));
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

    public class MyCountDownTimer extends CountDownTimer{
        public MyCountDownTimer(long startTime, long interval){
            super(startTime, interval);
        }

        @Override
        public void onFinish(){
            timerView.setText("Done");
        }

        @Override
        public void onTick(long millisUntilFinished){
            timerView.setText(""+String.format(FORMAT,
                    TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                            TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                    TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
        }
    }
}




