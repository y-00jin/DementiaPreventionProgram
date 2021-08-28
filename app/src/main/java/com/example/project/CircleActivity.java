package com.example.project;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.project.R;

public class CircleActivity extends AppCompatActivity {
   //멤버 변수 선언
    private CircleView mCircleView; //circleView id 값을 가진 뷰
    private int viewId;
    private int radioColor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.circle_main);

        mCircleView = (CircleView) findViewById(R.id.circleview);   //circleview id값을 가진 뷰 mCircleView에 저장


    }
    public void redraw(View v){ //circleview를 다시 그리는 redraw 함수
        mCircleView.setPaintColor(getColor());  //getColor()함수 값을 인자로 가지는 setPaintColor 함수 호출
        mCircleView.invalidate();   //화면 갱신

    }

    public void sendMessage(View view){ //정답 출력 함수
        viewId = view.getId();  //클릭된 버튼의 id값 가져오기
        Button btn = (Button)view;     //클릭된 버튼
        String btnText = btn.getText().toString();  // 클릭된 버튼의 Text를 가져와 String형으로 변환
        

        if(mCircleView.getNum().equals(btnText)){   //랜덤으로 리턴되는 num값을 받아 눌린 버튼의 텍스트와 같으면 실행
            Toast.makeText(getApplicationContext(), "답은 " + btnText + " 정답입니다!!", Toast.LENGTH_SHORT).show();   //메시지 출력
        }
        else{
            Toast.makeText(getApplicationContext(), "" +btnText + " 개 ) 틀렸습니다. 다시 세어보세요!", Toast.LENGTH_SHORT).show();

        }
    }

    public void onRadioBtnClicked(View view){   
        boolean checked = ((RadioButton)view).isChecked();  //라디오 버튼 체크 여부 확인

        switch (view.getId()){  //체크된 라디오 버튼의 id로 나눔
            case R.id.radio_red:    //red면
                if(checked){
                    radioColor = Color.RED; //radioColor에 빨간색 저장
                }
                break;
            case R.id.radio_blue: //blue면
                if(checked){
                    radioColor = Color.BLUE;    //radioColor에 파란색 저장
                }
                break;
        }
    }

    public int getColor(){  //radioColor 리턴
        return radioColor;
    }   //저장한 radioColor return해주는 함수

}