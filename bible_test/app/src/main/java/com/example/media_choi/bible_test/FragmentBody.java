package com.example.media_choi.bible_test;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Media_Choi on 2018-02-07.
 */

public class FragmentBody extends Fragment {

    TextView textCategory;
    TextView textTitle;
    TextView textBody;
    TextView textUnderTitle;
    TextView textChapter;

    //ArrayList<HashMap<String, String>> arrayList = new ArrayList<HashMap<String, String>>();
    String body;
    ArrayList<DataModel> data;


    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentbody, container, false);
        context = getContext();


        String json = null;
        try {
            InputStream is = context.getAssets().open("test.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();
            json = new String(buffer, "UTF-8");

            JSONObject obj = new JSONObject(json);
            JSONArray jsonArray = obj.getJSONArray("Bible");
            data = DataModel.fromJson(jsonArray);

//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject c = jsonArray.getJSONObject(i);
//
//                String id = c.getString("id");
//                String chapter = c.getString("chapter");
//                String category = c.getString("category");
//                String title = c.getString("title");
//                String body = c.getString("body");
//
//                // tmp hashmap for single student
//                HashMap<String, String> data = new HashMap<String, String>();
//
//                // adding every child node to HashMap key => value
//                data.put(id, id);
//                data.put(chapter, chapter);
//                data.put(category, category);
//                data.put(title, title);
//                arrayList.add(data);
//            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        textBody = (TextView) view.findViewById(R.id.textBody);
        //textBody.setText(arrayList.toString());
        textBody.setText(data.get(1).body);
        return view;
    }
}
