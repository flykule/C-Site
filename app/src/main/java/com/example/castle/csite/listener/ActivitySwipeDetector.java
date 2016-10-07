package com.example.castle.csite.listener;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;

import com.example.castle.csite.util.LogUtils;

/**
 * Created by castle on 16-10-7.
 * 自定义触摸监听
 */
public class ActivitySwipeDetector implements View.OnTouchListener {

    private Activity activity;
    static final int MIN_DISTANCE = 10;
    private float downX, downY, upX, upY;

    public ActivitySwipeDetector(final Activity activity) {
        this.activity = activity;
    }

    public final void onRightToLeftSwipe() {
    }

    public void onLeftToRightSwipe(){
        LogUtils.i( "LeftToRightSwipe!");
    }

    public void onTopToBottomSwipe(){
        LogUtils.i( "onTopToBottomSwipe!");
    }

    public void onBottomToTopSwipe(){
        LogUtils.i( "onBottomToTopSwipe!");
    }

    public boolean onTouch(View v, MotionEvent event) {
    LogUtils.d("收到点击");
        switch(event.getAction()){
            case MotionEvent.ACTION_DOWN: {
                downX = event.getX();
                downY = event.getY();
                //   return true;
            }
            case MotionEvent.ACTION_UP: {
                upX = event.getX();
                upY = event.getY();

                float deltaX = downX - upX;
                float deltaY = downY - upY;

                // swipe horizontal?
                if(Math.abs(deltaX)> MIN_DISTANCE){
                    // left or right
                    if(deltaX< 0) { this.onLeftToRightSwipe(); return true; }
                    if(deltaX> 0) { this.onRightToLeftSwipe(); return true; }
                } else { LogUtils.i( "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE); }

                // swipe vertical?
                if(Math.abs(deltaY)> MIN_DISTANCE){
                    // top or down
                    if(deltaY< 0) { this.onTopToBottomSwipe(); return true; }
                    if(deltaY> 0) { this.onBottomToTopSwipe(); return true; }
                } else { LogUtils.i( "Swipe was only " + Math.abs(deltaX) + " long, need at least " + MIN_DISTANCE); }

                //     return true;
            }
        }
        return false;
    }
}
