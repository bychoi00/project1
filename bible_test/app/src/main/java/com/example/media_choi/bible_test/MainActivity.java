package com.example.media_choi.bible_test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.InputStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    DBHelper dbHelper;
    Button buttonCheck;
    TextView textCategory;
    TextView textTitle;
    TextView textBody;
    TextView textUnderTitle;
    TextView textChapter;
    LinearLayout myLayout;
    String body;
    ArrayList<DataModel> data;
    DataModel jsonData;
    String id;

    public static final String FILE_NAME="bible_test.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //DB
        dbHelper = new DBHelper(this);

        //선언
        textCategory = (TextView) findViewById(R.id.textCategory);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textBody = (TextView) findViewById(R.id.textBody);
        textChapter = (TextView) findViewById(R.id.textChapter);
        textUnderTitle = (TextView) findViewById(R.id.textUnderTitle);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        myLayout = (LinearLayout) findViewById(R.id.mylayout);

        //json Data Load , id를 로컬DB를 통해 저장시켜뒀다가 종료시점 이후 불러오기***************************************
        id = "A10101";
        jsonData = DataLoad(id);

        textChapter.setText(jsonData.chapter);
        textCategory.setText(jsonData.category);
        textTitle.setText(jsonData.title);
        textBody.setText(jsonData.body);
        textUnderTitle.setText(jsonData.title);

        //이벤트
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShow();
            }
        });
        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "myLayout touch!", Toast.LENGTH_LONG).show();

                //json Data Load
                jsonData = DataLoad(id);

                textCategory.setText(jsonData.category);
                textTitle.setText(jsonData.title);
                textBody.setText(jsonData.body);
                textChapter.setText(jsonData.chapter);
                textUnderTitle.setText(jsonData.title);
            }
        });

    }


    private void dialogShow() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("구절암송 완료");
        builder.setMessage("우와! 다 외우셨나요?");
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //예 누를 시 다음구절로 점프, 프로그레스바 진행, 리스트 뷰에 체크

            }
        });
        builder.setNegativeButton("아직 덜 외웠어요", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //아니오 누를 시
            }
        });
        builder.show();
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


        for(int i = 0 ; i< data.size() ; i++){
            if( data.get(i).id.equals(id) ){
                int index = data.indexOf(id);
                dataModel.id = data.get(index+1).id;
                dataModel.body = data.get(index+1).body;
                dataModel.title = data.get(index+1).title;
                dataModel.category = data.get(index+1).category;
                dataModel.chapter = data.get(index+1).chapter;
            }
            else{
            }
        }


        return dataModel;
    }
}
