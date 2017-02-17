package com.example.guessit.guessit;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import java.io.File;

public class ImagePage extends AppCompatActivity {
    private ImageView imageView;
    private Uri file;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_page);
        imageView = (ImageView)findViewById(R.id.imageView2);
        file = Uri.fromFile(getOutputMediaFile());
        imageView.setImageURI(file);
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

    public void goToWaitingPg(View view) {
        Intent intent = new Intent(this, HintActivity.class);
        startActivity(intent);
    }
}
