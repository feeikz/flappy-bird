package com.example.flappy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences options = getSharedPreferences("save", MODE_PRIVATE);
        boolean tmp = options.getBoolean("value", true);
        Log.d("msg", String.valueOf(tmp));
        if(!tmp){
            AppConstants.initialization(this);
        }else{
            AppConstants.initialization2(this);
        }




    }

    public void openOptions(View view){
        Intent intent = new Intent(this,OptionsActivity.class);
        startActivity(intent);
        finish();
    }

    public void startGame(View view){
        Log.i("ImageButton", "clicked");
        Intent intent = new Intent(this, GameActivity.class);
        startActivity(intent);
        finish();
    }
}
