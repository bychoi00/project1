package com.example.media_choi.bible_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Media_Choi on 2018-02-14.
 */

public class DBHelper extends SQLiteOpenHelper{
    private static final String DB_NAME = "BIBLE";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "BIBLE_TABLE";
    private static final String COL_ID = "ID";
    private static final String COL_CHAPTER = "CHAPTER";
    private static final String COL_CATEGORY = "CATEGORY";
    private static final String COL_BODY = "BODY";
    private static final String COL_TITLE = "TITLE";

    SQLiteDatabase mSqLiteDatabase;

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "CREATE TABLE"+TABLE_NAME+"("+ COL_ID +" TEXT,"+ COL_CHAPTER + " TEXT,"+ COL_CATEGORY +" TEXT," + COL_TITLE + "TEXT," + COL_BODY + "TEXT);";
        db.execSQL(create_table);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertBible(String Id, String Chapter, String Category, String Title, String Body){
        mSqLiteDatabase = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID,Id);
        values.put(COL_CHAPTER,Chapter);
        values.put(COL_CATEGORY,Category);
        values.put(COL_TITLE,Title);
        values.put(COL_BODY,Body);

        mSqLiteDatabase.insert(TABLE_NAME,null,values);
        mSqLiteDatabase.close();
    }
}
