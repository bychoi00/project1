package com.example.media_choi.bible_test;

import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.io.InputStream;

/**
 * Created by Media_Choi on 2018-02-07.
 */

public class FragmentBody extends Fragment {

    TextView textCategory;
    TextView textTitle;
    TextView textBody;
    TextView textUnderTitle;
    TextView textChapter;

    private Context context;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragmentbody,container,false);
        context = getContext();

        String json = null;
        try {
            InputStream is = context.getAssets().open("test.json");
            int size = is.available();
            byte[] buffer = new byte[size];
            is.read(buffer);
            is.close();

            json = new String(buffer,"UTF-8");
            
        } catch (Exception e) {
            e.printStackTrace();
        }


        return view;
    }
}
