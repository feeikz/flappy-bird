package com.example.flappy.Classes;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

public class Database extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "FlappyBird.db";
    public static final String TABLE_NAME = "high_score";
    public static final String COL_1 = "ID";
    public static final String COL_2 = "SCORE";



    public Database(Context context){
        super(context, DATABASE_NAME, null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " +  TABLE_NAME + "(NAME INTEGER PRIMARY KEY,SCORE)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addScore(String score){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, score);
        long result = db.insert(TABLE_NAME,null, contentValues);

        if (result == -1){
            return false;
        }else return true;

    }

    public Cursor getListContests(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        return data;
    }

}
