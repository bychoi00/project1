package com.bibleapp.media_choi.bible_test;

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
    private LinearLayout myLayout;
    private DBHelper mDbHelper;
    private ArrayList<DataModel> bibles_parts = new ArrayList<>();

    private int count = 0;
    private int flag = 1;

    private static final String PART_A = "A.새로운 삶";
    private static final String PART_B = "B.그리스도를 전파함";
    private static final String PART_C = "C.하나님을 의뢰함";
    private static final String PART_D = "D.그리스도 제자의 자격";
    private static final String PART_E = "E.그리스도를 닮아 감";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //선언
        declar();

        //저장되어 있는 데이터 가져오기.*****************************************************************************************
        if (savedInstanceState != null) {
            count = savedInstanceState.getInt("count");
            flag = savedInstanceState.getInt("flag");
            declar();

        }

        //json Data Load , id를 로컬DB를 통해 저장시켜뒀다가 종료시점 이후 불러오기***************************************
        mDbHelper = new DBHelper(this);
        bibles_parts = mDbHelper.getPartsData(PART_A);

        //spinner 설정
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.number, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mySpinner.setAdapter(adapter);

        //초기화면 display , DB에 저장된 마지막 사용자화면 디스플레이
        textCategory.setText(bibles_parts.get(count).category_kor);
        textTitle.setText(bibles_parts.get(count).title_kor);
        textBody.setText(bibles_parts.get(count).body_kor);
        textChapter.setText(bibles_parts.get(count).chapter_kor);
        textUnderTitle.setText(bibles_parts.get(count).title_kor);

        //이벤트
        // 한/영 버튼 누를 시
        buttonChange.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (flag == 1) {
                    textCategory.setText(bibles_parts.get(count).category_eng);
                    textTitle.setText(bibles_parts.get(count).title_eng);
                    textBody.setText(bibles_parts.get(count).body_eng);
                    textChapter.setText(bibles_parts.get(count).chapter_eng);
                    textUnderTitle.setText(bibles_parts.get(count).title_eng);
                    flag = 0;
                } else if (flag == 0) {
                    textCategory.setText(bibles_parts.get(count).category_kor);
                    textTitle.setText(bibles_parts.get(count).title_kor);
                    textBody.setText(bibles_parts.get(count).body_kor);
                    textChapter.setText(bibles_parts.get(count).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(count).title_kor);
                    flag = 1;
                }
            }
        });

        myLayout.setOnTouchListener(new OnSwipeTouchListener(this) {
            public void onSwipeTop() {
            }

            public void onSwipeRight() {
                //layout 클릭 시 마다 텍스트 변경 디스플레이
                if (count == 0) {
                    textCategory.setText(bibles_parts.get(bibles_parts.size() - 1).category_kor);
                    textTitle.setText(bibles_parts.get(bibles_parts.size() - 1).title_kor);
                    textBody.setText(bibles_parts.get(bibles_parts.size() - 1).body_kor);
                    textChapter.setText(bibles_parts.get(bibles_parts.size() - 1).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(bibles_parts.size() - 1).title_kor);
                    count = bibles_parts.size() - 1;
                    flag = 1;
                } else {
                    count--;
                    textCategory.setText(bibles_parts.get(count).category_kor);
                    textTitle.setText(bibles_parts.get(count).title_kor);
                    textBody.setText(bibles_parts.get(count).body_kor);
                    textChapter.setText(bibles_parts.get(count).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(count).title_kor);
                    flag = 1;
                }
            }

            public void onSwipeLeft() {
                //layout 클릭 시 마다 텍스트 변경 디스플레이
                if ((count + 1) == bibles_parts.size()) {
                    textCategory.setText(bibles_parts.get(0).category_kor);
                    textTitle.setText(bibles_parts.get(0).title_kor);
                    textBody.setText(bibles_parts.get(0).body_kor);
                    textChapter.setText(bibles_parts.get(0).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(0).title_kor);
                    count = 0;
                    flag = 1;

                } else {
                    count++;
                    textCategory.setText(bibles_parts.get(count).category_kor);
                    textTitle.setText(bibles_parts.get(count).title_kor);
                    textBody.setText(bibles_parts.get(count).body_kor);
                    textChapter.setText(bibles_parts.get(count).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(count).title_kor);
                    flag = 1;
                }
            }

            public void onSwipeBottom() {
            }
        });

        mySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                //파트별로 설정했을 때
                Object i = parent.getItemAtPosition(position);
                if (i.equals(PART_A)) {
                    bibles_parts = mDbHelper.getPartsData(PART_A);
                    count = 0;

                    textCategory.setText(bibles_parts.get(count).category_kor);
                    textTitle.setText(bibles_parts.get(count).title_kor);
                    textBody.setText(bibles_parts.get(count).body_kor);
                    textChapter.setText(bibles_parts.get(count).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(count).title_kor);

                } else if (i.equals(PART_B)) {
                    bibles_parts = mDbHelper.getPartsData(PART_B);
                    count = 0;

                    textCategory.setText(bibles_parts.get(count).category_kor);
                    textTitle.setText(bibles_parts.get(count).title_kor);
                    textBody.setText(bibles_parts.get(count).body_kor);
                    textChapter.setText(bibles_parts.get(count).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(count).title_kor);

                } else if (i.equals(PART_C)) {
                    bibles_parts = mDbHelper.getPartsData(PART_C);
                    count = 0;

                    textCategory.setText(bibles_parts.get(count).category_kor);
                    textTitle.setText(bibles_parts.get(count).title_kor);
                    textBody.setText(bibles_parts.get(count).body_kor);
                    textChapter.setText(bibles_parts.get(count).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(count).title_kor);

                } else if (i.equals(PART_D)) {
                    bibles_parts = mDbHelper.getPartsData(PART_D);
                    count = 0;

                    textCategory.setText(bibles_parts.get(count).category_kor);
                    textTitle.setText(bibles_parts.get(count).title_kor);
                    textBody.setText(bibles_parts.get(count).body_kor);
                    textChapter.setText(bibles_parts.get(count).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(count).title_kor);

                } else if (i.equals(PART_E)) {
                    bibles_parts = mDbHelper.getPartsData(PART_E);
                    count = 0;

                    textCategory.setText(bibles_parts.get(count).category_kor);
                    textTitle.setText(bibles_parts.get(count).title_kor);
                    textBody.setText(bibles_parts.get(count).body_kor);
                    textChapter.setText(bibles_parts.get(count).chapter_kor);
                    textUnderTitle.setText(bibles_parts.get(count).title_kor);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                //아무것도 설정하지 않았을 때
            }
        });
    }

    private void declar() {
        //선언
        mySpinner = (Spinner) findViewById(R.id.mySpinner);
        textCategory = (TextView) findViewById(R.id.textCategory);
        textTitle = (TextView) findViewById(R.id.textTitle);
        textBody = (TextView) findViewById(R.id.textBody);
        textChapter = (TextView) findViewById(R.id.textChapter);
        textUnderTitle = (TextView) findViewById(R.id.textUnderTitle);
        buttonChange = (Button) findViewById(R.id.buttonChange);
        myLayout = (LinearLayout) findViewById(R.id.myLayout);
    }

    //Activity 파괴시 데이터 저장
    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt("count", count);
        outState.putInt("flag", flag);
    }

}
