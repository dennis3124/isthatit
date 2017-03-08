package com.example.guessit.guessit;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by zhan1803, yu599 on 2/16/2017.
 */

public class ResultPage extends AppCompatActivity {

    Button finishResultButton;
    Button scoreResultButton;
    Button exitGameButton;
    Button uploadPicButton;
    private static int RESULT_LOAD_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        finishResultButton = (Button) findViewById(R.id.finishResultButton);
        scoreResultButton = (Button) findViewById(R.id.scoreResultButton);
        exitGameButton = (Button) findViewById(R.id.exitGameButton2);
        uploadPicButton = (Button) findViewById(R.id.uploadPicButton);

        finishResultButton.setEnabled(false);

        finishResultButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ResultPage.this, HintActivity.class));
            }
        });

        scoreResultButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ResultPage.this, ScoreTable.class));
            }
        });

        exitGameButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(ResultPage.this, ExitGame.class));
            }
        });

        // Upload picture by hint giver from gallary if no one sent the correct picture
        uploadPicButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                //startActivity(new Intent(ResultPage.this, UploadPic.class));
                startActivityForResult(new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI), RESULT_LOAD_IMAGE);
            }
        });
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        try {
            // When an Image is picked

            if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK
                    && null != data) {
                finishResultButton.setEnabled(true);
                // Get the Image from data
                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};
                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();
                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                String imgDecodableString = cursor.getString(columnIndex);
                cursor.close();

                ImageView imageView = (ImageView) findViewById(R.id.correctImg);
                imageView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));
            }
            else {
                Toast.makeText(this, "You haven't picked Image", Toast.LENGTH_LONG).show();

            }

        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG).show();
        }

    }


}
