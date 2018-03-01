package com.bibleapp.media_choi.bible_test;

import android.content.ContentValues;
import android.content.Context;
import android.content.res.Resources;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by Media_Choi on 2018-02-14.
 */

public class DBHelper extends SQLiteOpenHelper {

    private static final String FILE_NAME = "bible.json";
    private static final String DB_NAME = "BIBLE";
    private static final int DB_VERSION = 1;
    private static final String TABLE_NAME = "BIBLE_TABLE";
    private static final String COL_ID = "ID";
    private static final String COL_PARTS = "PARTS";
    private static final String COL_CHAPTER_KOR = "CHAPTER_KOR";
    private static final String COL_CHAPTER_ENG = "CHAPTER_ENG";
    private static final String COL_CATEGORY_KOR = "CATEGORY_KOR";
    private static final String COL_CATEGORY_ENG = "CATEGORY_ENG";
    private static final String COL_BODY_KOR = "BODY_KOR";
    private static final String COL_BODY_ENG = "BODY_ENG";
    private static final String COL_TITLE_KOR = "TITLE_KOR";
    private static final String COL_TITLE_ENG = "TITLE_ENG";

    private Resources mResources;
    private SQLiteDatabase db;
    ArrayList<DataModel> data;
    private static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_ID + " TEXT, " +
            COL_PARTS+ " TEXT, " +
            COL_CHAPTER_KOR + " TEXT, " +
            COL_CHAPTER_ENG + " TEXT, " +
            COL_CATEGORY_KOR + " TEXT, " +
            COL_CATEGORY_ENG + " TEXT, " +
            COL_TITLE_KOR + " TEXT, " +
            COL_TITLE_ENG + " TEXT, " +
            COL_BODY_KOR + " TEXT, " +
            COL_BODY_ENG + " TEXT " + ")";

    private static final String DELETE_QUERY = "DROP TABLE IF EXISTS " + TABLE_NAME;


    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);

        mResources = context.getResources();

        db = this.getWritableDatabase();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table Created...");

        try {
            JsonToDB(db);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DELETE_QUERY);
        Log.d("Database operations", "Database updated...");
        onCreate(db);
    }

    public void JsonToDB(SQLiteDatabase db) throws IOException, JSONException {

        //json데이터 로드
        String json = null;
        DataModel dataModel = new DataModel();
        InputStream inputStream = null;

        //DB insert param
        final String DB_ID = "id";
        final String DB_PARTS = "parts";
        final String DB_CHAPTER_KOR = "chapter_kor";
        final String DB_CHAPTER_ENG = "chapter_eng";
        final String DB_CATEGORY_KOR = "category_kor";
        final String DB_CATEGORY_ENG = "category_eng";
        final String DB_TITLE_KOR = "title_kor";
        final String DB_TITLE_ENG = "title_eng";
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

            for (int i = 0; i < jsonArray.length(); i++) {
                String id;
                String parts;
                String chapter_kor;
                String chapter_eng;
                String category_kor;
                String category_eng;
                String title_kor;
                String title_eng;
                String body_kor;
                String body_eng;

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getString(DB_ID);
                parts = jsonObject.getString(DB_PARTS);
                chapter_kor = jsonObject.getString(DB_CHAPTER_KOR);
                chapter_eng = jsonObject.getString(DB_CHAPTER_ENG);
                category_kor = jsonObject.getString(DB_CATEGORY_KOR);
                category_eng = jsonObject.getString(DB_CATEGORY_ENG);
                title_kor = jsonObject.getString(DB_TITLE_KOR);
                title_eng = jsonObject.getString(DB_TITLE_ENG);
                body_kor = jsonObject.getString(DB_BODY_KOR);
                body_eng = jsonObject.getString(DB_BODY_ENG);

                //insert DB
                ContentValues values = new ContentValues();
                values.put(COL_ID, id);
                values.put(COL_PARTS, parts);
                values.put(COL_CHAPTER_KOR, chapter_kor);
                values.put(COL_CHAPTER_ENG, chapter_eng);
                values.put(COL_CATEGORY_KOR, category_kor);
                values.put(COL_CATEGORY_ENG, category_eng);
                values.put(COL_TITLE_KOR, title_kor);
                values.put(COL_TITLE_ENG, title_eng);
                values.put(COL_BODY_KOR, body_kor);
                values.put(COL_BODY_ENG, body_eng);

                db.insert(TABLE_NAME, null, values);
                Log.d("Database operations", "One row inserted...");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DataModel> getAllData() {

        ArrayList<DataModel> bibles = new ArrayList<>();
        db = this.getReadableDatabase();

        //Query 문
        String select_all = "Select * from " + TABLE_NAME;
        Cursor cursor = db.rawQuery(select_all, null);

        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex(COL_ID));
            String parts = cursor.getString(cursor.getColumnIndex(COL_PARTS));
            String chapter_kor = cursor.getString(cursor.getColumnIndex(COL_CHAPTER_KOR));
            String chapter_eng = cursor.getString(cursor.getColumnIndex(COL_CHAPTER_ENG));
            String category_kor = cursor.getString(cursor.getColumnIndex(COL_CATEGORY_KOR));
            String category_eng = cursor.getString(cursor.getColumnIndex(COL_CATEGORY_ENG));
            String title_kor = cursor.getString(cursor.getColumnIndex(COL_TITLE_KOR));
            String title_eng = cursor.getString(cursor.getColumnIndex(COL_TITLE_ENG));
            String body_kor = cursor.getString(cursor.getColumnIndex(COL_BODY_KOR));
            String body_eng = cursor.getString(cursor.getColumnIndex(COL_BODY_ENG));

            DataModel dataModel = new DataModel(id, parts, chapter_kor, chapter_eng, category_kor, category_eng, title_kor, title_eng, body_kor, body_eng);
            bibles.add(dataModel);
        }

        return bibles;
    }

    public ArrayList<DataModel> getPartsData(String Parts){
        ArrayList<DataModel> bibles = new ArrayList<>();
        db = this.getReadableDatabase();

        //Query 문
        String select_parts = "Select * from " + TABLE_NAME + " Where PARTS = " + "'"+Parts+"'";
        Cursor cursor = db.rawQuery(select_parts, null);

        while (cursor.moveToNext()) {

            String id = cursor.getString(cursor.getColumnIndex(COL_ID));
            String parts = cursor.getString(cursor.getColumnIndex(COL_PARTS));
            String chapter_kor = cursor.getString(cursor.getColumnIndex(COL_CHAPTER_KOR));
            String chapter_eng = cursor.getString(cursor.getColumnIndex(COL_CHAPTER_ENG));
            String category_kor = cursor.getString(cursor.getColumnIndex(COL_CATEGORY_KOR));
            String category_eng = cursor.getString(cursor.getColumnIndex(COL_CATEGORY_ENG));
            String title_kor = cursor.getString(cursor.getColumnIndex(COL_TITLE_KOR));
            String title_eng = cursor.getString(cursor.getColumnIndex(COL_TITLE_ENG));
            String body_kor = cursor.getString(cursor.getColumnIndex(COL_BODY_KOR));
            String body_eng = cursor.getString(cursor.getColumnIndex(COL_BODY_ENG));

            DataModel dataModel = new DataModel(id, parts, chapter_kor, chapter_eng, category_kor, category_eng, title_kor, title_eng, body_kor, body_eng);
            bibles.add(dataModel);
        }

        return bibles;
    }

}
