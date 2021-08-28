package com.example.project;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class CalcView extends View{

    //선언
    private Paint paint = new Paint();
    private Path path = new Path();
    private int paintColor = 0xFF000000;
    private Paint canvasPaint;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;

    public CalcView(Context context) { super(context); }
    public CalcView(Context context, AttributeSet attrs ){

        super(context, attrs);
        paint.setAntiAlias(true);   //도형 끝을 부드럽게 해줌
        paint.setStrokeWidth(10f);  //선 두께
        paint.setColor(paintColor); //paintColor로 선 색 지정
        paint.setStyle(Paint.Style.STROKE); //선으로
        paint.setStrokeJoin(Paint.Join.ROUND);  //선 끝이 둥근모양

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh){
        super.onSizeChanged(w, h, oldw, oldh);  //부모 메서드 먼저 호출하여 w, h값 조사해 뷰의 크기 알아냄
        canvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888);    //Bitmap 객체를 이용해 화면에 그림
        drawCanvas = new Canvas(canvasBitmap);  //canvasBitmap으로 Canvas 객체 생성
        canvasPaint = new Paint(Paint.DITHER_FLAG); //Paint.DITHER_FLAG : 비트의 설정 또는 클리어
    }


    @Override
    protected void onDraw(Canvas canvas){
        canvas.drawBitmap(canvasBitmap, 0, 0, canvasPaint); //화면에 그릴 비트맵, 위치, 위치, Paint 객체
        canvas.drawPath(path, paint);   //paint로 주어진 path 그리기
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        float touchX = event.getX();    //터치된 x좌표
        float touchY = event.getY();    //터치된 y좌표
        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:   //눌렀을 때
                path.moveTo(touchX, touchY);    //패스의 시작점 지정
                break;
            case MotionEvent.ACTION_MOVE:   //누른 상태로 움직이고 있을 때
                path.lineTo(touchX, touchY);    //직선 추가
                break;
            case MotionEvent.ACTION_UP: //누른걸 떼었을 때
                drawCanvas.drawPath(path, paint);   //패스 그리기
                path.reset();   //리셋
                break;
            default:
                return false;
        }
        invalidate();
        return true;
    }

    public void setColor(int newColor, int strokeWidth){
        invalidate();
        paint.setColor(newColor);   //newColor로 색 변경
        paint.setStrokeWidth(strokeWidth);  //strokeWidth로 두께 변경
    }

    public void removeAll(int mColor){
        paint.setColor(mColor); //선 색 원래의 색으로
        canvasBitmap = Bitmap.createBitmap(getWidth(),getHeight(),Bitmap.Config.ARGB_8888);    //Bitmap 객체를 이용해 화면에 그림
        drawCanvas = new Canvas(canvasBitmap);  //canvasBitmap으로 Canvas 객체 생성
        canvasPaint = new Paint(Paint.DITHER_FLAG);
        canvasBitmap.eraseColor(Color.WHITE);   //화면 리셋

    }

    public int getColor(){  //색깔 리턴
        return paint.getColor();
    }
}
