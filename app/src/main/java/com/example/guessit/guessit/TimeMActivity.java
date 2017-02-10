package com.example.guessit.guessit;

import android.app.Activity;
import android.os.Bundle;
import android.util.DisplayMetrics;

/**
 * Created by zhan1803 on 2/7/2017.
 */
public class TimeMActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timem);

    }
}


//
//import android.app.Activity;
//import android.os.Bundle;
//import android.util.DisplayMetrics;
//import android.view.View;
//import android.widget.Button;
//
///**
// * Created by yuzhuyang on 2/9/17.
// */
//
//public class TimeMActivity extends Activity {
//
//    public Button btnOne,btnTwo, btnSetTime;
//    public  int time = 0;
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        btnOne = (Button) findViewById(R.id.btnOne);
//        btnTwo = (Button) findViewById(R.id.btnTwo);
//        btnSetTime = (Button) findViewById(R.id.btnSetTime);
//        btnOne.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                time = 60;
//            }
//        });
//
//
//        btnTwo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                time = 120;
////                if(btnTwo.getText().toString().equals("按钮不可用")){
////                    btnOne.setEnabled(false);
////                    btnTwo.setText("按钮可用");
////                }else{
////                    btnOne.setEnabled(true);
////                    btnTwo.setText("按钮不可用");
////                }
//                //TimeCActivity(Integer.parseInt(btnTwo.getText().toString()));
//            }
//        });
//
//        btnSetTime.setOnClickListener(new View.OnClickListener() {
//            public void onClick(View v) {
//                if (time == 0) {
//                    //set time
//                } else {
//                    //TimeCActivity(time);
//                }
//
//            }
//        });
//
//    }
//
//}

