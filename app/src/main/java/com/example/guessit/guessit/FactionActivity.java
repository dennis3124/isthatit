 package com.example.guessit.guessit;
        import android.content.Intent;
        import android.view.View;
        import android.widget.*;

        import android.support.v7.app.AppCompatActivity;
        import android.os.Bundle;
        import java.util.concurrent.TimeUnit;
        import android.util.Log;
        import java.util.List;


//public class FactionActivity extends AppCompatActivity {
        import org.w3c.dom.Text;


public class FactionActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faction);

        Button viewScoreTableButton = (Button) findViewById(R.id.viewScoreButton);
        viewScoreTableButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FactionActivity.this, ScoreTable.class));
            }
        });

        Button viewHintButton = (Button) findViewById(R.id.viewHintButton);
        viewHintButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                startActivity(new Intent(FactionActivity.this, HintPage.class));
            }
        });
    }

    public void monsterFaction(View view) {

        Intent intent = new Intent(this, HumanActivity.class);
        startActivity(intent);
    }

    public void humanFaction(View view) {
        Intent intent = new Intent(this, HumanActivity.class);
        startActivity(intent);
    }

    public void toServer(View view) {
        Intent intent = new Intent(this,ServerActivity.class);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Constants.socket.emit("leaveRoom", Constants.gameId);
        return;
    }

//    public class MyCountDownTimer extends CountDownTimer{
//        public MyCountDownTimer(long startTime, long interval){
//            super(startTime, interval);
//        }
//
//        @Override
//        public void onFinish(){
//            timerView.setText("Done");
//        }

}
