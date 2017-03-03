package com.example.guessit.guessit;

import android.Manifest;
import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.os.CountDownTimer;
import android.widget.TextView;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;


public class GamePage extends AppCompatActivity {
    Button takePic;
    Button viewPic;
    Button testResult;
    Button exitGame;
    private Uri file;
    private ImageView imageView;
    Button viewScoreTableButton;
    Button viewHintButton;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    public TextView timerView;
    private static final String FORMAT = "%02d:%02d";
    private long startTime = 30*1000;
    private long interval = 1000;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_page);
        takePic = (Button)findViewById(R.id.takeimg);
        viewPic = (Button)findViewById(R.id.confirmimg);
        testResult = (Button)findViewById(R.id.testResult);
        exitGame = (Button)findViewById(R.id.exitGameButton);
        imageView = (ImageView)findViewById(R.id.imageview);
        viewHintButton = (Button)findViewById(R.id.viewHintButton);
        viewScoreTableButton = (Button)findViewById(R.id.viewScoreButton);
        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        progressBar.setMax((int)startTime);

        viewScoreTableButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(GamePage.this, ScoreTable.class));
            }
        });
        viewHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(GamePage.this, HintPage.class));
            }
        });

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
            public void onTick(long millisUntilFinished) {
                long progress = millisUntilFinished/1000;
                progressBar.setProgress((int)(progressBar.getMax()-progress));

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
                timerView.setText("Done");
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
}
