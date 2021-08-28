package com.example.project;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.Random;

public class CircleView extends View {
    private int num;
    private static Random random = new Random();    //랜덤객체
    private int pColor;

    public CircleView(Context context){ super(context); }
    public CircleView(Context context, AttributeSet attrs ){ super(context, attrs); }

    @Override
    protected void onDraw(Canvas canvas){
        super.onDraw(canvas);
        int width = getWidth(); //canvas의 너비 가져오기
        int height = getHeight();   //canvas의 높이 가져오기
        num = random.nextInt(10 )+1;   //랜덤으로 값 받아오기


        for(int i=0; i<num ;i++){
            float x = random.nextInt(width);    //너비만큼 랜덤으로 x값 지정
            float y = random.nextInt(height);   //높이만큼 랜덤으로 y값 지정



            Paint circleColor = makePaint(pColor);  //라디오 버튼으로 지정받은 색으로 원 색 지정

            canvas.drawCircle(x,y,30, circleColor );    //원 그리기
        }

    }

    private Paint makePaint(int color){
        Paint p = new Paint();
        p.setColor(color);
        return (p);
    }
    public String getNum(){ // 원의 개수 num값 리턴
        String s = String.valueOf(num);
        return s;
    }
    public void setPaintColor(int color){
        pColor = color;
    }   //color값 지정
}
