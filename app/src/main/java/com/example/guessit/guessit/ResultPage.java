package com.example.guessit.guessit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by zhan1803 on 2/16/2017.
 */

public class ResultPage extends AppCompatActivity {

    Button finishResultButton;
    Button scoreResultButton;
    Button exitGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_page);
        finishResultButton = (Button) findViewById(R.id.finishResultButton);
        scoreResultButton = (Button) findViewById(R.id.scoreResultButton);
        exitGameButton = (Button) findViewById(R.id.exitGameButton2);

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
    }
}
