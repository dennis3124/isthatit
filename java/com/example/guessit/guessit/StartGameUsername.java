package com.example.guessit.guessit;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class StartGameUsername extends AppCompatActivity {

    EditText username;
    Button gotofaction;

    TextView gamecodeview;

    Players player;
    Games game;
    PlayerDBHandler pdb = new PlayerDBHandler(this);

    String gameIdNumber;

    private DatabaseReference mDatabase;

    //June edit
    //EditText GameCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_game_username);



        //database
        mDatabase = FirebaseDatabase.getInstance().getReference().child("GameCode");
        gamecodeview = (TextView)findViewById(R.id.gamecode_view);

        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                String gamecode = dataSnapshot.getValue().toString();
                //System.out.println("this is gamecode" + gamecode);
                gamecodeview.setText("GameCode:  " + gamecode);

//                try {
//                    int gamecodenumber = 1 + Integer.parseInt(gamecode);
//                } catch(NumberFormatException nfe) {
//                    System.out.println("Could not parse " + nfe);
//                }
//              String key = mDatabase.push(hashmap).getKey();
                int gamecodenumber = Integer.parseInt(gamecode) + 1;

                System.out.println(gamecodenumber);

                String result = String.format("%0" + 4 + "d", Integer.parseInt(String.valueOf(gamecodenumber)));
                mDatabase.setValue(result);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        username = (EditText)findViewById(R.id.usernamefield);

        gotofaction = (Button)findViewById(R.id.gotofaction);
        gotofaction.setEnabled(false);

        //Games game = new Games(Integer.parseInt(gameIdNumber));
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
            Constants.playerName = username.getText().toString();
           // pdb.addPlayer(player);
            // Added player, so increment count
            gotofaction.setEnabled(true);



        }
        // Check if in database table already
    }
}
