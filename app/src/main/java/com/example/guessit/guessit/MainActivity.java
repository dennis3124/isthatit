package com.example.guessit.guessit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import java.util.List;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Global var. for ready & notification
    private AlertDialog.Builder alert_toHintGiver;
    private AlertDialog.Builder alert_setHint;
    private AlertDialog.Builder alert_hint;
    private Button button_toHintGiver;
    private Button button_setHint;
    private Button button_hint;
    private String hint = "";
    final Context context = this;

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

        //Pop up notification if all players are ready



        button_toHintGiver = (Button) findViewById(R.id.button_toHintGiver);
        button_toHintGiver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert_toHintGiver = new AlertDialog.Builder(context);
                alert_toHintGiver.setMessage("You are selected as the hint giver for this round.");
                alert_toHintGiver.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(MainActivity.this, "You are the hint giver!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert_toHintGiver.show();
            }
        });

        // Set hint when the hint giver is notified


        button_setHint = (Button) findViewById(R.id.button_setHint);
        button_setHint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                alert_setHint = new AlertDialog.Builder(context);
                alert_setHint.setMessage("Please enter a hint: ");

                final EditText input = new EditText(context);
                final int maxLength = 20;
                final int minLength = 1;



                input.setFilters(new InputFilter[] {new InputFilter.LengthFilter(maxLength + 1)});
                input.setInputType(InputType.TYPE_CLASS_TEXT);
                alert_setHint.setView(input);

                input.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        if (s.length() < minLength || s.length() > maxLength)
                            new AlertDialog.Builder(context).setTitle("The number of characters should be between 1 and 20")
                                    .setPositiveButton(android.R.string.ok, null).show();
                    }
                });

                alert_setHint.setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    if (input.getText().equals) {

                    }
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        hint = input.getText().toString();
                        Toast.makeText(context, "Hint set successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert_setHint.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                alert_setHint.show();
            }

        });








        // Check hint



        button_hint = (Button) findViewById(R.id.button_hint);
        button_hint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //when this button is clicked, show the alert
                alert_hint = new AlertDialog.Builder(context);
                alert_hint.setTitle("Hint");
                alert_hint.setMessage(hint);
                alert_hint.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(MainActivity.this, "You are the hint giver!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert_hint.show();
            }
        });

    }
}
