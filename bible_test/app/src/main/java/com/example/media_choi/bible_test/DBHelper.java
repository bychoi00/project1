package com.example.media_choi.bible_test;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;

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
    public static final String CREATE_QUERY = "CREATE TABLE " + TABLE_NAME + " (" +
            COL_ID + " TEXT, " +
            COL_CHAPTER + " TEXT, " +
            COL_CATEGORY + " TEXT, " +
            COL_TITLE + "TEXT, " +
            COL_BODY_KOR + "TEXT, " +
            COL_BODY_ENG + "TEXT)";



    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_QUERY);
        Log.d("Database operations", "Table Created...");
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

    private DataModel DataLoad(String id) {

        //json데이터 로드
        String json = null;
        DataModel dataModel = new DataModel();

        try {
            InputStream is = getAssets().open(FILE_NAME);
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("Bible");

            for(int i = 0; i < jsonArray.length(); i++){
                String id;
                String category;
                String title;
                String body_kor;
                String body_eng;
                String chapter;

                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getString("id");
                category = jsonObject.getString("category");
                title = jsonObject.getString("title");
                body_kor = jsonObject.getString("body_kor");
                body_eng = jsonObject.getString("body_eng");
                chapter = jsonObject.getString("chapter");
            }


            data = DataModel.fromJson(jsonArray);

        } catch (Exception e) {
            e.printStackTrace();
        }


        for(int i = 0 ; i< data.size(); i++){
            //if( data.get(i).id.equals(id) ){
            int index = data.indexOf(id);
            dataModel.id = data.get(index+1).id;
            dataModel.body_kor = data.get(index+1).body_kor;
            dataModel.body_eng = data.get(index+1).body_eng;
            dataModel.title = data.get(index+1).title;
            dataModel.category = data.get(index+1).category;
            dataModel.chapter = data.get(index+1).chapter;

            //DB에 저장
            mDbHelper.insertBible(dataModel.id, dataModel.chapter, dataModel.category, dataModel.title, dataModel.body_kor, dataModel.body_eng);

            //}
            //else{
            //}
        }

        return dataModel;
    }
}
