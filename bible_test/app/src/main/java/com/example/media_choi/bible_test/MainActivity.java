package com.example.media_choi.bible_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Spinner mySpinner;
    Button buttonChange;
    TextView textCategory;
    TextView textTitle;
    TextView textBody;
    TextView textUnderTitle;
    TextView textChapter;
    LinearLayout myLayout;
    DBHelper mDbHelper;
    ArrayList<DataModel> bibles = new ArrayList<>();
    int count = 1;
    int flag = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //선언
        mySpinner = (Spinner) findViewById(R.id.mySpinner);
        textCategory = (TextView) findViewById(R.id.textCategory);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textBody = (TextView) findViewById(R.id.textBody);
        textChapter = (TextView) findViewById(R.id.textChapter);
        textUnderTitle = (TextView) findViewById(R.id.textUnderTitle);
        buttonChange = (Button) findViewById(R.id.buttonChange);
        myLayout = (LinearLayout) findViewById(R.id.myLayout);

        //json Data Load , id를 로컬DB를 통해 저장시켜뒀다가 종료시점 이후 불러오기***************************************
        mDbHelper = new DBHelper(this);
        bibles = mDbHelper.getAllData(); //db에서 모든 파일 꺼내오기

        //spinner 설정
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);

        //초기화면 display , DB에 저장된 마지막 사용자화면 디스플레이
        textCategory.setText(bibles.get(0).category_kor);
        textTitle.setText(bibles.get(0).title_kor);
        textBody.setText(bibles.get(0).body_kor);
        textChapter.setText(bibles.get(0).chapter_kor);
        textUnderTitle.setText(bibles.get(0).title_kor);


        //이벤트
        // 한/영 버튼 누를 시
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 1) {
                    textCategory.setText(bibles.get(count - 1).category_eng);
                    textTitle.setText(bibles.get(count - 1).title_eng);
                    textBody.setText(bibles.get(count - 1).body_eng);
                    textChapter.setText(bibles.get(count - 1).chapter_eng);
                    textUnderTitle.setText(bibles.get(count - 1).title_eng);
                    flag = 0;
                } else if (flag == 0) {
                    textCategory.setText(bibles.get(count - 1).category_kor);
                    textTitle.setText(bibles.get(count - 1).title_kor);
                    textBody.setText(bibles.get(count - 1).body_kor);
                    textChapter.setText(bibles.get(count - 1).chapter_kor);
                    textUnderTitle.setText(bibles.get(count - 1).title_kor);
                    flag = 1;
                }
            }
        });

        //레이아웃 클릭 시 애니메이션활용해서 페이지 넘기는 식으로 다음절 표시
        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //layout 클릭 시 마다 텍스트 변경 디스플레이
                if (count == bibles.size()) {
                    textCategory.setText(bibles.get(0).category_kor);
                    textTitle.setText(bibles.get(0).title_kor);
                    textBody.setText(bibles.get(0).body_kor);
                    textChapter.setText(bibles.get(0).chapter_kor);
                    textUnderTitle.setText(bibles.get(0).title_kor);
                    count = 1;
                } else {
                    textCategory.setText(bibles.get(count).category_kor);
                    textTitle.setText(bibles.get(count).title_kor);
                    textBody.setText(bibles.get(count).body_kor);
                    textChapter.setText(bibles.get(count).chapter_kor);
                    textUnderTitle.setText(bibles.get(count).title_kor);
                    count++;
                }

            }
        });

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                //파트별로 설정했을 때
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //아무것도 설정하지 않았을 때
            }
        });

    }
}
