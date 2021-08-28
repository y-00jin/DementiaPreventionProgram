package com.example.project;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;


public class CalcActivity extends AppCompatActivity {
        private static Random random = new Random();
        private CalcView mCalcView;
        private int num1;
        private int num2;
        TextView tvNum1;
        TextView tvNum2;
        private TextView tvOp;
        private int index;
        private int result;
        private TextView EtAnswer;
        private String strResult;
        private CharSequence strEtAnswer;
        private int mColor;
        private int bColor;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calc_main);
        mCalcView = (CalcView) findViewById(R.id.drawCalc);   //drawCalc id값을 가진 뷰 mCalcView에 저장

        Spinner spinner = (Spinner)findViewById(R.id.spinner);  //스피너 id 받아와서 spinner에 저장
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.color_array, android.R.layout.simple_spinner_item);  // 리소스로 부터 ArrayAdapter 생성
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); //가장 표준적인 뷰로 항목 표시
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {    //스피너의 항목이 클릭되었을 때
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                if(parent.getItemAtPosition(position).toString().equals("검은색")){    //선택된 항목의 내용이 검은색일때
                    mCalcView.setColor(Color.BLACK, 10);    //선 색깔 black

                }
                else if(parent.getItemAtPosition(position).toString().equals("빨간색")){   //선택된 항목의 내용이 빨간색일때
                    mCalcView.setColor(Color.RED, 10);      //선 색깔 red

                }
                else if(parent.getItemAtPosition(position).toString().equals("파란색")){   //선택된 항목의 내용이 파란색일때
                    mCalcView.setColor(Color.BLUE, 10);     //선 색깔 blue

                }
                else if(parent.getItemAtPosition(position).toString().equals("초록색")){   //선택된 항목의 내용이 초록색일때
                    mCalcView.setColor(Color.GREEN, 10);    //선 색깔 green

                }
                else {                                                                      //그외
                    mCalcView.setColor(Color.MAGENTA, 10);  //선 색깔 magenta

                }

                bColor = mCalcView.getColor();  //지우개 두번 클릭시 색 지정할 때 사용

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    public void onBtnQuestion(View view){


        String[] arrOp = {"+","-","*","/"}; // 연산자 배열에 미리 저장

        index = random.nextInt(4);    // 연산자에서 꺼낼 index값 랜덤으로 지정
        num1 = random.nextInt(100)+1;   //첫번째 숫자 랜덤 지정
        num2 = random.nextInt(10)+1;    //두번쨰 숫자 랜덤 지정

        tvNum1 = (TextView)findViewById(R.id.textNum1); //textView id값이 textNum1인거 가져옴
        tvNum2 = (TextView)findViewById(R.id.textNum2); //textView id값이 textNum2인거 가져옴
        tvOp = (TextView)findViewById(R.id.textOp);     //textView id값이 textNumOp인거 가져옴

        tvNum1.setText(num1+"");    //랜덤으로 지정된 num1로 textNum1 지정
        tvNum2.setText(num2+"");    //랜덤으로 지정된 num2로 textNum2 지정
        tvOp.setText(arrOp[index]); //랜덤으로 지정된 index를 이용해 textOp 지정

        switch (arrOp[index]){  //랜덤으로 지정된 연산자가 +, -, *, / 인가 나눠서 계산
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "*":
                result = num1 * num2;
                break;
            case "/":
                result = num1 / num2;
                break;
        }
    }

    public void onBtnAnswer(View view){ //정답 버튼을 누를 시

        EtAnswer = (TextView)findViewById(R.id.etAnswer);   //EditText id가 etAnswer인거 가져옴

        strResult = String.valueOf(result); //int 값인 result를 String 으로 변환
        if(EtAnswer.getText().toString().equals(strResult)){        //EditText에 입력한 값이 result와 같으면
            Toast.makeText(getApplicationContext(), "답은 : " + strResult + " 정답입니다!" , Toast.LENGTH_SHORT).show();
            mColor = mCalcView.getColor();
            mCalcView.removeAll(mColor);  //화면 리셋

        }
        else{
            Toast.makeText(getApplicationContext(), "틀렸습니다. 다시 계산해보세요!" , Toast.LENGTH_SHORT).show();
        }
        EtAnswer.setText("");   //EditText를 ""으로 지정

    }

    public void onBtnRemove(View view){ //지우개 버튼 클릭시

        if (mCalcView.getColor() == Color.WHITE){   //색이 흰색일 경우
            mCalcView.setColor(bColor, 10);    //펜 검은색으로
            view.setBackgroundColor(Color.rgb(206,242,121));
        }
        else{
            mCalcView.setColor(Color.WHITE, 80);    //흰색으로 (지우개 역할)
            view.setBackgroundColor(Color.rgb(178,235,244));
        }
    }

    public void onBtnRemoveAll(View view){  //모두 지우기
        mColor = mCalcView.getColor();  //현재 펜 색 가져오기
        mCalcView.removeAll(mColor);    //모두 지우기
    }
}
