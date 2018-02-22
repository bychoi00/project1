package com.example.media_choi.bible_test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Media_Choi on 2018-02-09.
 */

public class DataModel {
    public String getId() {
        return id;
    }

    public String getChapter() {
        return chapter;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getBody_kor() {
        return body_kor;
    }

    public String getBody_eng() {
        return body_eng;
    }


    public static DataModel fromJson(JSONObject jsonObject) {
        DataModel d = new DataModel();
        try {
            d.id = jsonObject.getString("id");
            d.chapter = jsonObject.getString("chapter");
            d.category = jsonObject.getString("category");
            d.title = jsonObject.getString("title");
            d.body_kor = jsonObject.getString("body_kor");
            d.body_eng = jsonObject.getString("body_eng");
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return  d;
    }

    public static ArrayList<DataModel> fromJson(JSONArray jsonArray){
        JSONObject jsonObject;
        ArrayList<DataModel> arrayList = new ArrayList<DataModel>(jsonArray.length());
        for(int i =0 ; i < jsonArray.length(); i++){
            try{
                jsonObject = jsonArray.getJSONObject(i);
            }catch (JSONException e){
                e.printStackTrace();
                continue;
            }

            DataModel data = DataModel.fromJson(jsonObject);
            if(data !=null){
                arrayList.add(data);
            }
        }
        return arrayList;
    }

    public DataModel(String id, String chapter, String category, String title, String body_kor, String body_eng) {
        this.id = id;
        this.chapter = chapter;
        this.category = category;
        this.title = title;
        this.body_kor = body_kor;
        this.body_eng = body_eng;
    }

    public DataModel()
    {

    }

    String id;
    String chapter;
    String category;
    String title;
    String body_kor;
    String body_eng;

}
