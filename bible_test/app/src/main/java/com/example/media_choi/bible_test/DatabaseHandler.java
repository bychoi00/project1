package com.example.media_choi.bible_test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Media_Choi on 2018-02-13.
 */

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final String DB_NAME = "BIBLE";
    private static final int DB_VERSION = 1;

    public DatabaseHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
