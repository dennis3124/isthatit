package com.example.guessit.guessit;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.os.CountDownTimer;
import android.widget.TextView;

import com.github.nkzawa.emitter.Emitter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import static com.example.guessit.guessit.HintActivity.timeRound;


public class GamePage extends AppCompatActivity {
    Button takePic;
    Button viewPic;
<<<<<<< HEAD
    Button testResult;
    Button exitGame;
=======
>>>>>>> 7f3c027eb2d3f3fb91ba1aeaa3f4e390eeceee67
    private Uri file;
    private ImageView imageView;
    Button viewScoreTableButton;
    Button viewHintButton;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    public TextView timerView;
    private static final String FORMAT = "%02d:%02d";
    private long startTime = timeRound * 1000 * 60;
    private long interval = 1000;
    private AlertDialog.Builder alert_hint;
    TextView showName;
    final Context context = this;
    private Integer[] Imgid = {R.id.imageView12,R.id.imageView13,R.id.imageView14,R.id.imageView15,R.id.imageView16,R.id.imageView17,R.id.imageView18,R.id.imageView19 };
    public int count=0;
    private ImageView avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        JSONObject obj = new JSONObject();
        try {
            obj.put("gameId", Constants.gameId);
            obj.put("avatarName", Constants.avatarName);
            obj.put("playerName", Constants.playerName);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        Constants.socket.emit("getAllPlayers", obj);
        Constants.socket.on("playersInRoom", handleAllPlayers);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        takePic = (Button)findViewById(R.id.takeimg);
        viewPic = (Button)findViewById(R.id.confirmimg);

        //testResult = (Button)findViewById(R.id.testResult);
        //exitGame = (Button)findViewById(R.id.exitGameButton);

        imageView = (ImageView)findViewById(R.id.imageview);
        viewHintButton = (Button)findViewById(R.id.viewHintButton);
        viewScoreTableButton = (Button)findViewById(R.id.viewScoreButton);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        showName = (TextView)findViewById(R.id.showuser);
        avatar = (ImageView)findViewById(R.id.avatarImg);

        viewScoreTableButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(GamePage.this, ScoreTable.class));
            }
        });
        viewHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //startActivity(new Intent(GamePage.this, HintPage.class));
                alert_hint = new AlertDialog.Builder(GamePage.this);
                alert_hint.setTitle("Hint");
                alert_hint.setMessage(HintActivity.hint);
                alert_hint.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        //Toast.makeText(MainActivity.this, "You are the hint giver!", Toast.LENGTH_SHORT).show();
                    }
                });
                alert_hint.show();
                //startActivity(new Intent(GamePage.this, HintPage.class));
            }
        });
<<<<<<< HEAD

        testResult.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(GamePage.this, ResultPage.class));
            }
        });

        exitGame.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(GamePage.this, ExitGame.class));
            }
        });

=======
>>>>>>> 7f3c027eb2d3f3fb91ba1aeaa3f4e390eeceee67
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            takePic.setEnabled(false);
            ActivityCompat.requestPermissions(this, new String[] { Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE }, 0);
        }

        //eg


        timerView = (TextView) findViewById(R.id.countDownTimer);

        //countDownTimer = new MyCountDownTimer(startTime, interval);

        //Countdown timer  + Progress Bar
        countDownTimer = new CountDownTimer(startTime, interval) { // Parameters has to be changed to what the room initiator set.
            @Override
            public void onTick(long millisUntilFinished) { // milliusuntilfinished is the amt of time until finished
                int progress = (int)(millisUntilFinished/1000); // Need to get this value right
                progressBar.setProgress(progressBar.getMax()-progress);

                // Need code for written time
                timerView.setText(""+String.format(FORMAT,
                        TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished) - TimeUnit.HOURS.toMinutes(
                                TimeUnit.MILLISECONDS.toHours(millisUntilFinished)),
                        TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) - TimeUnit.MINUTES.toSeconds(
                                TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));
            }

            @Override
            public void onFinish() {
                Log.d("Time", "Up!");
                timerView.setText("You are out of time!");
                View rootview = getWindow().getDecorView().getRootView();
                confirmImage(rootview);
                // When done, it should go to image page.
            }
        };
        countDownTimer.start();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file);
            }
        }
    }

    public void takeImage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }

    public void confirmImage(View view) {
        Intent intent = new Intent(this, ImagePage.class);
        startActivity(intent);
    }

    private static File getOutputMediaFile(){
        File mediaStorageDir = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "CameraDemo");

        if (!mediaStorageDir.exists()){
            if (!mediaStorageDir.mkdirs()){
                Log.d("CameraDemo", "failed to create directory");
                return null;
            }
        }

        //String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String uniqueStamp = "guessITOfficial";
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ uniqueStamp + ".jpg");
    }


    // Get username for player -- Why this doesn't work?
    /*public Emitter.Listener newPlayerEntered = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONObject obj = (JSONObject) args[0];
            try {
                String playerEntered = obj.getString("playerName");
                Log.d("Player name is: ", playerEntered);
                changeName(playerEntered);
            } catch (JSONException e) {
                return;
            }
        }
    };

    // Method to change name
    public void changeName(String name) {
        Log.d("Why is it not out", name);
        showName.setText(name);
    }*/

    // I need the avatar as well
    public void createProfile(String avatarName, final String name) {
        Log.d("Avatar name is: ", avatarName);
        //Get the iconid from avatar name to link it to drawable folder
        final int iconId =  getResources().getIdentifier("drawable/" + avatarName, null,context.getPackageName());
        Log.d("IconId is: ", Integer.toString(iconId));
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Log.d("Count is: ", Integer.toString(count));
                avatar.setImageResource(iconId);
                showName.setText(name);
                count++;
            }
        });
    }

    public Emitter.Listener handleAllPlayers = new Emitter.Listener() {
        @Override
        public void call(Object... args) {
            JSONArray obj = (JSONArray) args[0];
            String avatar;
            String name;

            try {
                for(int i =0 ; i < obj.length();i++){
                    JSONObject singleObj = (JSONObject) obj.getJSONObject(i);
                    avatar = singleObj.getString("avatarName");
                    name = singleObj.getString("playerName");
                    createProfile(avatar, name);
                }

            } catch (JSONException e) {
                return;
            }
        }
    };
}
