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

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

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
    DBHelper mDbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //선언
        textCategory = (TextView) findViewById(R.id.textCategory);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textBody = (TextView) findViewById(R.id.textBody);
        textChapter = (TextView) findViewById(R.id.textChapter);
        textUnderTitle = (TextView) findViewById(R.id.textUnderTitle);
        buttonCheck = (Button) findViewById(R.id.buttonCheck);
        myLayout = (LinearLayout) findViewById(R.id.mylayout);
        mDbHelper = new DBHelper(this);

        //json Data Load , id를 로컬DB를 통해 저장시켜뒀다가 종료시점 이후 불러오기***************************************


        textChapter.setText(jsonData.chapter);
        textCategory.setText(jsonData.category);
        textTitle.setText(jsonData.title);
        textBody.setText(jsonData.body_kor);
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
                Toast.makeText(getApplicationContext(), "myLayout touch!", Toast.LENGTH_SHORT).show();

                //json Data Load
                textCategory.setText(jsonData.category);
                textTitle.setText(jsonData.title);
                textBody.setText(jsonData.body_kor);
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


}
