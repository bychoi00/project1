package com.bibleapp.media_choi.bible_test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Media_Choi on 2018-02-09.
 */

public class DataModel {

    public static DataModel fromJson(JSONObject jsonObject) {
        DataModel d = new DataModel();
        try {
            d.id = jsonObject.getString("id");
            d.parts = jsonObject.getString("parts");
            d.chapter_kor = jsonObject.getString("chapter_kor");
            d.chapter_eng = jsonObject.getString("chapter_eng");
            d.category_kor = jsonObject.getString("category_kor");
            d.category_eng = jsonObject.getString("category_eng");
            d.title_kor = jsonObject.getString("title_kor");
            d.title_eng = jsonObject.getString("title_eng");
            d.body_kor = jsonObject.getString("body_kor");
            d.body_eng = jsonObject.getString("body_eng");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return d;
    }

    public static ArrayList<DataModel> fromJson(JSONArray jsonArray) {
        JSONObject jsonObject;
        ArrayList<DataModel> arrayList = new ArrayList<DataModel>(jsonArray.length());
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
            } catch (JSONException e) {
                e.printStackTrace();
                continue;
            }

            DataModel data = DataModel.fromJson(jsonObject);
            if (data != null) {
                arrayList.add(data);
            }
        }
        return arrayList;
    }

    public DataModel() {

    }

    public DataModel(String id, String parts, String chapter_kor, String chapter_eng, String category_kor, String category_eng, String title_kor, String title_eng, String body_kor, String body_eng) {
        this.id = id;
        this.parts = parts;
        this.chapter_kor = chapter_kor;
        this.chapter_eng = chapter_eng;
        this.category_kor = category_kor;
        this.category_eng = category_eng;
        this.title_kor = title_kor;
        this.title_eng = title_eng;
        this.body_kor = body_kor;
        this.body_eng = body_eng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getChapter_kor() {
        return chapter_kor;
    }

    public void setChapter_kor(String chapter_kor) {
        this.chapter_kor = chapter_kor;
    }

    public String getChapter_eng() {
        return chapter_eng;
    }

    public void setChapter_eng(String chapter_eng) {
        this.chapter_eng = chapter_eng;
    }

    public String getCategory_kor() {
        return category_kor;
    }

    public void setCategory_kor(String category_kor) {
        this.category_kor = category_kor;
    }

    public String getCategory_eng() {
        return category_eng;
    }

    public void setCategory_eng(String category_eng) {
        this.category_eng = category_eng;
    }

    public String getTitle_kor() {
        return title_kor;
    }

    public void setTitle_kor(String title_kor) {
        this.title_kor = title_kor;
    }

    public String getTitle_eng() {
        return title_eng;
    }

    public void setTitle_eng(String title_eng) {
        this.title_eng = title_eng;
    }

    public String getBody_kor() {
        return body_kor;
    }

    public void setBody_kor(String body_kor) {
        this.body_kor = body_kor;
    }

    public String getBody_eng() {
        return body_eng;
    }

    public void setBody_eng(String body_eng) {
        this.body_eng = body_eng;
    }

    public String getParts() {
        return parts;
    }

    public void setParts(String parts) {
        this.parts = parts;
    }

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

}
