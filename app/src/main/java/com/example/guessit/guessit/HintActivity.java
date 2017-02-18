package com.example.guessit.guessit;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextWatcher;
import android.util.Log;
import java.util.List;
import java.util.Timer;
import java.util.prefs.PreferenceChangeEvent;

import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

public class HintActivity extends AppCompatActivity {

    // Global var. for ready & notification
    private AlertDialog.Builder alert_toHintGiver;
    private AlertDialog.Builder alert_setHint;
    private AlertDialog.Builder alert_hint;
    private Button button_toHintGiver;
    private Button button_setHint;
    private Button button_hint;
    private TextView gameId;
    static String hint = "";
    final Context context = this;
    public ImageView imageArray[] = new ImageView[8];
    public int count=0;
    private Integer[] Imgid = {R.id.imageView12,R.id.imageView13,R.id.imageView14,R.id.imageView15,R.id.imageView16,R.id.imageView17,R.id.imageView18,R.id.imageView19 };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hint);
        JSONObject obj = new JSONObject();
        try {
            obj.put("gameId", Constants.gameId);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Constants.socket.emit("getAllPlayers", obj);
        for (; count < obj.length(); count++) {
            imageArray[count] = (ImageView) findViewById(Imgid[count]);
        }

        //GameDBHandler db = new GameDBHandler(this);

        //insert a game (Test)
//        Log.d("Insert: ", "Inserting ..");
//        db.createGame(new Games(1));
//        db.createGame(new Games(2));
//        db.createGame(new Games(3));

        //Read all games
//        Log.d("Reading: ", "Reading all Games");
//        List<Games> games = db.getAllGames();

//        for(Games game : games) {
//            String log = "Id: " + game.getId() + " Number of Player: " + game.getNumPlayers();
//            Log.d("Games: : ", log);
//        }

        //Pop up notification if all players are ready
        gameId = (TextView) findViewById(R.id.textView16);
        String newGameIdString = gameId.getText().toString();
        newGameIdString += Constants.gameId;
        gameId.setText(newGameIdString);
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
                startActivity(new Intent(view.getContext(), GamePage.class));
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
                /*
                Intent intent = new Intent(view.getContext(), HintPage.class);
                intent.putExtra("Hint", hint);
                startActivity(intent);

                SharedPreferences share_hint = getSharedPreferences("hint_to_hintPage", Context.MODE_PRIVATE);
                SharedPreferences.Editor editor = share_hint.edit();
                editor.putString("hint_", hint);
                //editor.remove("hint");
                editor.apply();
                */

                alert_setHint.show();
            }

        });








        // Check hint

//        button_hint = (Button) findViewById(R.id.button_hint);
//        button_hint.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                //when this button is clicked, show the alert
//                alert_hint = new AlertDialog.Builder(context);
//                alert_hint.setTitle("Hint");
//                alert_hint.setMessage(hint);
//                alert_hint.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int id) {
//                        //Toast.makeText(MainActivity.this, "You are the hint giver!", Toast.LENGTH_SHORT).show();
//                    }
//                });
//                alert_hint.show();
//            }
//        });

    }
}