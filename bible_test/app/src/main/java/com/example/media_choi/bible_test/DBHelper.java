package com.example.media_choi.bible_test;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Media_Choi on 2018-02-14.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String FILE_NAME = "test.json";
    private static final String DB_NAME = "BIBLE";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "BIBLE_TABLE";
    private static final String COL_ID = "ID";
    private static final String COL_CHAPTER = "CHAPTER";
    private static final String COL_CATEGORY = "CATEGORY";
    private static final String COL_BODY_KOR = "BODY_KOR";
    private static final String COL_BODY_ENG = "BODY_ENG";
    private static final String COL_TITLE = "TITLE";
    private Resources mResources;
    private DBHelper mDbHelper;
    private SQLiteDatabase db;
    ArrayList<DataModel> data;
    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_ID + " TEXT, " +
            COL_CHAPTER + " TEXT, " +
            COL_CATEGORY + " TEXT, " +
            COL_TITLE + " TEXT, " +
            COL_BODY_KOR + " TEXT, " +
            COL_BODY_ENG + " TEXT)";


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        mResources = context.getResources();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table Created...");

        DataLoad();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP IF TABLE EXISTS " + TABLE_NAME);
        onCreate(db);
        Log.d("Database operations", "Database updated...");
    }

    public void insertBible(String Id, String Chapter, String Category, String Title, String Body_KOR, String Body_ENG) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(COL_ID, Id);
        values.put(COL_CHAPTER, Chapter);
        values.put(COL_CATEGORY, Category);
        values.put(COL_TITLE, Title);
        values.put(COL_BODY_KOR, Body_KOR);
        values.put(COL_BODY_ENG, Body_ENG);

        db.insert(TABLE_NAME, null, values);
        Log.d("Database operations", "One row inserted...");
        db.close();
    }

    public void DataLoad() {

        //json데이터 로드
        String json = null;
        DataModel dataModel = new DataModel();
        InputStream inputStream = null;

        //DB insert param
        final String DB_ID = "id";
        final String DB_CHAPTER = "chapter";
        final String DB_CATEGORY = "category";
        final String DB_TITLE = "title";
        final String DB_BODY_KOR = "body_kor";
        final String DB_BODY_ENG = "body_eng";

        try {
            inputStream = mResources.getAssets().open(FILE_NAME);
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, "UTF-8");

            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("Bible");

            for(int i = 0; i < jsonArray.length(); i++){
                String id;
                String chapter;
                String category;
                String title;
                String body_kor;
                String body_eng;

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getString(DB_ID);
                chapter = jsonObject.getString(DB_CHAPTER);
                category = jsonObject.getString(DB_CATEGORY);
                title = jsonObject.getString(DB_TITLE);
                body_kor = jsonObject.getString(DB_BODY_KOR);
                body_eng = jsonObject.getString(DB_BODY_ENG);

                //insert DB
                //mDbHelper.insertBible(id,chapter,category,title,body_kor,body_eng);

                db = this.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put(COL_ID, id);
                values.put(COL_CHAPTER, chapter);
                values.put(COL_CATEGORY, category);
                values.put(COL_TITLE, title);
                values.put(COL_BODY_KOR, body_kor);
                values.put(COL_BODY_ENG, body_eng);

                db.insert(TABLE_NAME, null, values);
                Log.d("Database operations", "One row inserted...");
                db.close();
                Log.d("Insert DB","Inserted Success");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
