package com.example.guessit.guessit;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.DisplayMetrics;
import android.widget.TextView;

/**
 * Created by zhan1803 on 2/7/2017.
 */
public class HintPage extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.view_hint_page);
/*
        SharedPreferences share_hint = getApplication().getSharedPreferences("hint_to_hintPage", Context.MODE_PRIVATE);
        String hint = share_hint.getString("hint_", "");
        TextView view_hint = (TextView) findViewById(R.id.currentRoundHint);
        view_hint.setText(hint);
        */
/*
        DisplayMetrics hintPageDisplayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(hintPageDisplayMetrics);

        int tableWidth = hintPageDisplayMetrics.widthPixels;
        int tableHeight = hintPageDisplayMetrics.heightPixels;

        getWindow().setLayout((int)(tableWidth*0.85), (int)(tableHeight*.5));
*/

    }
}