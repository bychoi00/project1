package com.example.media_choi.bible_test;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ProgressBar progressBar;
    Button buttonCheck;
    Button buttonChange;
    TextView textPersent;
    TextView textCategory;
    TextView textTitle;
    TextView textBody;
    TextView textUnderTitle;
    TextView textChapter;
    LinearLayout myLayout;
    LinearLayout successLayout;
    DBHelper mDbHelper;
    ArrayList<DataModel> bibles = new ArrayList<>();
    int count = 1;
    int flag=0;

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
        buttonChange = (Button) findViewById(R.id.buttonChange);
        progressBar = (ProgressBar)findViewById(R.id.progressbar);
        textPersent = (TextView)findViewById(R.id.textPersent);
        myLayout = (LinearLayout) findViewById(R.id.mylayout);
        successLayout = (LinearLayout)findViewById(R.id.successLayout);

        //json Data Load , id를 로컬DB를 통해 저장시켜뒀다가 종료시점 이후 불러오기***************************************
        mDbHelper = new DBHelper(this);
        bibles = mDbHelper.getAllData(); //db에서 모든 파일 꺼내오기

        //초기화면 display , DB에 저장된 마지막 사용자화면 디스플레이
        textCategory.setText(bibles.get(0).category_kor);
        textTitle.setText(bibles.get(0).title_kor);
        textBody.setText(bibles.get(0).body_kor);
        textChapter.setText(bibles.get(0).chapter_kor);
        textUnderTitle.setText(bibles.get(0).title_kor);

        //progress bar
        progressBar.setProgress(50);

        //이벤트
        // 체크버튼 누를 시
        buttonCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialogShow();
            }
        });

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
                    flag=0;
                }
                else if (flag == 0){
                    textCategory.setText(bibles.get(count - 1).category_kor);
                    textTitle.setText(bibles.get(count - 1).title_kor);
                    textBody.setText(bibles.get(count - 1).body_kor);
                    textChapter.setText(bibles.get(count - 1).chapter_kor);
                    textUnderTitle.setText(bibles.get(count - 1).title_kor);
                    flag =1;
                }
            }
        });

        //레이아웃 클릭 시 애니메이션활용해서 페이지 넘기는 식으로 다음절 표시
        myLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "myLayout touch!", Toast.LENGTH_SHORT).show();

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
