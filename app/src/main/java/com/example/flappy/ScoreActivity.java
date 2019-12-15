package com.example.flappy;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.flappy.Classes.Database;

import java.util.ArrayList;

public class ScoreActivity extends AppCompatActivity {
    ListView listView;
    Database database;
    EditText editText;
    Button addButton;

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.high_score_layout);

        database = new Database(this);
        listView = (ListView) findViewById(R.id.listview);
        

        ArrayList<String> arrayList = new ArrayList<>();
        Cursor data = database.getListContests();

        if(data.getCount() == 0){
            Toast.makeText(ScoreActivity.this,"Database is Empty", Toast.LENGTH_LONG).show();
        }
        else{
            while(data.moveToNext()){
                arrayList.add(data.getString(1));
                ListAdapter listAdapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
                listView.setAdapter(listAdapter);
            }
        }

    }

    public void menu(View view){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    public void AddData(String newEntry){
        boolean insertData = database.addScore(newEntry.toString());

        if(insertData){
            Toast.makeText(ScoreActivity.this,"Succesfully Added", Toast.LENGTH_LONG).show();
        }
        else  Toast.makeText(ScoreActivity.this,"Somethings went wrong :( ", Toast.LENGTH_LONG).show();

    }
}
