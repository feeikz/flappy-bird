package com.example.flappy;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flappy.Classes.Database;

public class GameOver extends AppCompatActivity {

    TextView tvScore, tvPersonalBest;
    Database database;
    EditText editText;
    String tmp;
    boolean send;
    int scoreX;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_over);
        send = false;
        scoreX = 0;

        database = new Database(this);
        tvScore = findViewById(R.id.tvScore);
        tvPersonalBest = findViewById(R.id.PersonalBest);
        editText = (EditText)findViewById(R.id.editText);
        int score = getIntent().getExtras().getInt("score");

        SharedPreferences pref = getSharedPreferences("MyPref", 0);
        int scoreSP = pref.getInt("scoreSP",0);
        SharedPreferences.Editor editor = pref.edit();

        if(score > scoreSP && score > 0){
            editText.setVisibility(View.VISIBLE);
            scoreSP = score;
            scoreX=score;
            editor.putInt("scoreSP",scoreSP);
            editor.commit();
            send=true;
        }


        tvScore.setText(""+score);
        tvPersonalBest.setText(""+scoreSP);
    }

    public void restart(View view){
        if(send){
            tmp = editText.getText().toString() + " \nSCORE:    " + String.valueOf(scoreX);
            AddData(tmp);
            tmp="";
        }
        editText.setVisibility(View.INVISIBLE);
        Intent intent = new Intent(this,MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void exit(View view){
        finish();
    }


    public void AddData(String newEntry){
        boolean insertData = database.addScore(newEntry);

        if(insertData){
            Toast.makeText(GameOver.this,"Succesfully Added", Toast.LENGTH_LONG).show();
        }
        else  Toast.makeText(GameOver.this,"Somethings went wrong :( ", Toast.LENGTH_LONG).show();

    }
}
