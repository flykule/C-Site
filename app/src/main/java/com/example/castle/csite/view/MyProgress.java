package com.example.castle.csite.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.widget.ProgressBar;

import com.example.castle.csite.util.FileUtils;
import com.example.castle.csite.util.LogUtils;

/**
 * @author 灰太狼
 * @time 2016/10/4  15:56
 * @desc 自定义的进度条
 */
public class MyProgress extends ProgressBar {
    String text;
    Paint mPaint;

    public MyProgress(Context context) {
        super(context);
        // TODO Auto-generated constructor stub
        LogUtils.d("MyProgress", "1");
        initText();
    }

    public MyProgress(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        // TODO Auto-generated constructor stub

        LogUtils.d("MyProgress", "2");
        initText();
    }


    public MyProgress(Context context, AttributeSet attrs) {
        super(context, attrs);
        // TODO Auto-generated constructor stub
        LogUtils.d("MyProgress", "3");
        initText();
    }

    @Override
    public synchronized void setProgress(int progress) {
        // TODO Auto-generated method stub
        setText(progress);
        super.setProgress(progress);

    }

    @Override
    protected synchronized void onDraw(Canvas canvas) {
        // TODO Auto-generated method stub
        super.onDraw(canvas);
        //this.setText();
        Rect rect = new Rect();
        this.mPaint.getTextBounds(this.text, 0, this.text.length(), rect);
        int x = (getWidth() / 2) - rect.centerX();
        int y = (getHeight() / 2) - rect.centerY();
        canvas.drawText(this.text, x, y, this.mPaint);
    }

    //初始化，画笔
    private void initText() {
        this.mPaint = new Paint();        
        this.mPaint.setColor(Color.BLACK);     
       
    }

    //设置文字内容
    private void setText() {

        setText(this.getProgress());
    }

    //设置文字内容
    private void setText(int progress) {
      
        this.text = "主存储" + FileUtils.getSDTotalSize() + "/可用" + FileUtils.getSDAvailableSize();
    }


}
