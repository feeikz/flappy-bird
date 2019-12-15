package com.example.flappy;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import androidx.annotation.Nullable;

public class OptionsActivity extends Activity {

    Switch sw,sw1;
    Settings settings;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.options_layout);
        sw = (Switch) findViewById(R.id.switch2);
        sw1 = (Switch) findViewById(R.id.switch1);
        settings = new Settings();
        SharedPreferences options = getSharedPreferences("save", 0);
        sw.setChecked(options.getBoolean("value",false));
        SharedPreferences options2 = getSharedPreferences("save2",0);
        sw1.setChecked(options2.getBoolean("value2",false));

        sw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save",0).edit();
                    editor.putBoolean("value",true);
                    editor.apply();
                    sw.setChecked(true);
                    //AppConstants.setTmp(true);
                   // settings.setDay(false);
                }
                else{
                    SharedPreferences.Editor editor = getSharedPreferences("save",0).edit();
                    editor.putBoolean("value",false);
                    editor.apply();
                    sw.setChecked(false);
                   // AppConstants.setTmp(false);
                  //  settings.setDay(false);
                }
            }
        });

        sw1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(sw1.isChecked()){
                    SharedPreferences.Editor editor = getSharedPreferences("save2",0).edit();
                    editor.putBoolean("value2",true);
                    editor.apply();
                    sw1.setChecked(true);

                }
                else{
                    SharedPreferences.Editor editor = getSharedPreferences("save2",0).edit();
                    editor.putBoolean("value2",false);
                    editor.apply();
                    sw1.setChecked(false);
                }
            }
        });
    }



    public void OpenMenu(View view){
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }



}



