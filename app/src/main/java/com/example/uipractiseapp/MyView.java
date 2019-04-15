package com.example.uipractiseapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import androidx.annotation.Nullable;

public class MyView extends View {
    public static final String TAG = "MyViewCustom";
    private Paint mPaint;

    public MyView(Context context) {
        this(context, null);
    }

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    private void init() {
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d(TAG, "onMeasure");
        int WidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int HeightMode = MeasureSpec.getMode(heightMeasureSpec);
        int WidthSize = MeasureSpec.getSize(widthMeasureSpec);
        int HeightSize = MeasureSpec.getSize(heightMeasureSpec);
        switch (WidthMode) {
            case MeasureSpec.EXACTLY:
                Log.d(TAG, "WidthMode====EXACTLY" + WidthMode + "    " + "HeightMode====" + HeightMode + "    " + "WidthSize====" + WidthSize + "    " + "HeightSize====" + HeightSize);
                break;
            case MeasureSpec.AT_MOST:
                Log.d(TAG, "WidthMode====AT_MOST" + WidthMode + "    " + "HeightMode====" + HeightMode + "    " + "WidthSize====" + WidthSize + "    " + "HeightSize====" + HeightSize);
                break;
            case MeasureSpec.UNSPECIFIED:
                Log.d(TAG, "WidthMode====UNSPECIFIED" + WidthMode + "    " + "HeightMode====" + HeightMode + "    " + "WidthSize====" + WidthSize + "    " + "HeightSize====" + HeightSize);
                break;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        canvas.drawLine(50, 50, 450, 50, mPaint);
    }


}
