package com.example.media_choi.bible_test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button1 = (Button)findViewById(R.id.button1);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switchFragment();
            }
        });
    }

    private void switchFragment() {
            getFragmentManager().beginTransaction().setCustomAnimations(R.animator.enter_anim,R.animator.exit_anim).replace(R.id.main_frame,new FragmentBody()).commit();
    }
}
