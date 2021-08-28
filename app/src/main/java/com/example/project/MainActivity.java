package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btnCalc = (Button)findViewById(R.id.btnCalc);    //btnCalc 아이디를 가진 뷰 가져오기
        Button btnCircle = (Button)findViewById(R.id.btnCircle);    //btnCircle 아이디를 가진 뷰 가져오기


        btnCircle.setOnClickListener(new View.OnClickListener() {   //btnCircle 버튼 클릭되면
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), CircleActivity.class);  //다른 액티비티 실행할 수 있게함
                startActivity(intent); //화면이동
            }
        });


        btnCalc.setOnClickListener(new View.OnClickListener() {   //btnCalc버튼 클릭되면
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(getApplicationContext(), CalcActivity.class);  //다른 액티비티 실행할 수 있게함
                startActivity(intent2); //화면이동
            }
        });
    }
}