package com.example.flappy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import com.example.flappy.Classes.AppConstants;
import com.example.flappy.Classes.Database;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import com.example.flappy.Classes.Database;

public class MainActivity extends AppCompatActivity {

    Database database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        database = new Database(this);
        SharedPreferences options = getSharedPreferences("save", 0);
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

    public void exit(View view){
        finish();
    }

    public void highscore(View view){
        Intent intent = new Intent(this, ScoreActivity.class);
        startActivity(intent);
        finish();
    }
}
