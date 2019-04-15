package com.example.uipractiseapp.utils;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

public class ScreenAdapterUtils {

    private static ScreenAdapterUtils screenAdapterUtils;

    private static  final float STANDARD_WIDTH=1080;
    private static final float STANDARD_HEIGHT = 1920;

    //这屏幕里是显示的宽高
    private int mDisplayWidth;
    private int mDisplayHeight;


    ScreenAdapterUtils(Context context) {
        if(mDisplayWidth==0|| mDisplayHeight==0){
            WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager!=null){
                DisplayMetrics displayMetrics =new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                if(displayMetrics.widthPixels>displayMetrics.heightPixels){
                    //横屏
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDisplayHeight= displayMetrics.widthPixels;
                }else{
                    mDisplayWidth = displayMetrics.widthPixels;
                    mDisplayHeight= displayMetrics.heightPixels- getStatusHeight(context);
                }
            }

        }
    }

    public int getStatusHeight(Context context){
        int resId = context.getResources().getIdentifier("status_bar_height","dimen","android" );
            if(resId>0){
                return context.getResources().getDimensionPixelSize(resId);
            }
            return 0;
    }

   public  static ScreenAdapterUtils getInstance( Context context){
        if(screenAdapterUtils ==null){
            screenAdapterUtils = new ScreenAdapterUtils(context.getApplicationContext());
        }
        return screenAdapterUtils;
    }

  public float  getHorizontalScale(){
        return mDisplayWidth/STANDARD_WIDTH;
  }

  public float getVerticalScale(){
        return mDisplayHeight/STANDARD_HEIGHT;
  }

}
