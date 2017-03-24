package com.example.guessit.guessit;

import android.Manifest;
import android.app.AlertDialog;

import android.app.ProgressDialog;
import android.content.Context;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.provider.MediaStore;
import android.provider.SyncStateContract;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.os.Bundle;
import android.support.v4.content.CursorLoader;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.content.pm.PackageManager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.os.CountDownTimer;
import android.widget.TextView;
import android.widget.Toast;


import com.android.internal.http.multipart.MultipartEntity;
import com.github.nkzawa.emitter.Emitter;
import com.github.nkzawa.socketio.client.Url;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;


import static android.R.attr.data;
import static com.example.guessit.guessit.Constants.gameId;
import static com.example.guessit.guessit.Constants.testUrl;
import static com.example.guessit.guessit.HintActivity.timeRound;



public class GamePage extends AppCompatActivity {
    Map<String,Object> data= new HashMap<String,Object>();
    Button takePic;
    Button viewPic;
    Button testResult;
    Button exitGame;

    static String path; // file path

    private Uri file;
    private ImageView imageView;
    Button viewScoreTableButton;
    Button viewHintButton;
    ProgressBar progressBar;
    CountDownTimer countDownTimer;
    public TextView timerView;
    private static final String FORMAT = "%02d:%02d";
    private long startTime = 130*1000;
    private long interval = 1000;
    private AlertDialog.Builder alert_hint;

    TextView showName;
    final Context context = this;
    private Integer[] Imgid = {R.id.imageView12,R.id.imageView13,R.id.imageView14,R.id.imageView15,R.id.imageView16,R.id.imageView17,R.id.imageView18,R.id.imageView19 };
    public int count=0;
    private ImageView avatar;
    byte[] ba;


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


        timerView = (TextView) findViewById(R.id.countDownTimer);

        //Countdown timer  + Progress Bar
        countDownTimer = new CountDownTimer(startTime, interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                int progress = (int)(millisUntilFinished/1000);
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
                // When done, it should go to image page.
                View rootview = getWindow().getDecorView().getRootView();
                confirmImage(rootview);


                timerView.setText("Done");
                // When done, it should go to image page.

            }
        };
        countDownTimer.start(); // Start the timer
    }

    // Results after taking the picture
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 100) {
            if (resultCode == RESULT_OK) {
                imageView.setImageURI(file); // set the picture on the view using the uri
            }
        }
    }

    // Function upload to server
    private void upload() {
        Log.d("Filepath", path);
        // Decodes a file path into bitmap
        Bitmap bm = BitmapFactory.decodeFile(path);
        // Create an output stream in which the data is written into a byte array
        ByteArrayOutputStream bao = new ByteArrayOutputStream();
        // Write a compressed version of the bitmap to the outputstream
        bm.compress(Bitmap.CompressFormat.JPEG, 90, bao);
        // create a byte array ba from the output stream
        ba = bao.toByteArray();
        Log.d("Gameid??", Constants.gameId);
        data.put("gameId", Constants.gameId);
        // execute in background?
        new uploadToServer().execute();
    }

    // Take the image, get the outputmedia file, and store it in that file
    public void takeImage(View view) {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        file = Uri.fromFile(getOutputMediaFile());
        intent.putExtra(MediaStore.EXTRA_OUTPUT, file);

        startActivityForResult(intent, 100);
    }

    // Upload the image to the server and go to image page
    public void confirmImage(View view) {
        upload();
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

        String uniqueStamp = "guessITOfficial";
        path = mediaStorageDir.getPath() + File.separator + "IMG_guessITOfficial.jpg";
        return new File(mediaStorageDir.getPath() + File.separator +
                "IMG_"+ uniqueStamp + ".jpg");
    }

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

    public class uploadToServer extends AsyncTask<Void, Void, String> {
        // Runs a progress dialog
        private ProgressDialog pd = new ProgressDialog(GamePage.this);
        protected void onPreExecute() {
            super.onPreExecute();
            pd.setMessage("Wait image uploading!");
            pd.show();
        }

        @Override
        protected String doInBackground(Void... params) {
            // Create an arraylist named namevaluepairs
            ArrayList<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
            // Store byte as string
            String ba1 = Base64.encodeToString(ba, Base64.DEFAULT);
            nameValuePairs.add(new BasicNameValuePair("base64", ba1));
            // Store name of image
            nameValuePairs.add(new BasicNameValuePair("ImageName", "IMG_guessITOfficial.jpg"));
            nameValuePairs.add(new BasicNameValuePair("GameID", Constants.gameId));
            try {
                HttpClient httpclient = new DefaultHttpClient();
                HttpPost httppost = new HttpPost("http://10.186.11.237:8080/pic");
                // Entity that can be sent or received with a HTTP msg
                httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                HttpResponse response = httpclient.execute(httppost);
                String st = EntityUtils.toString(response.getEntity());
                Log.v("log_tag", "In the try Loop" + st);

            } catch (Exception e) {
                Log.v("log_tag", "Error in http connection " + e.toString());
            }
            return "Success";

        }

        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            // Hide progress dialog
            pd.hide();
            pd.dismiss();
        }

    }

}

