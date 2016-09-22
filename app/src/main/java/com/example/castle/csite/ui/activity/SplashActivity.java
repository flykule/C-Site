package com.example.castle.csite.ui.activity;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.animation.AlphaAnimation;
import android.widget.ImageView;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.util.ActivityUtil;
import com.example.castle.csite.view.BindLayout;

/**
 * @author 灰太狼
 * @time 2016/9/21  22:11
 * @desc 广告页
 */
@BindLayout(id = R.layout.activity_splash)
public class SplashActivity extends BaseActivity {

    private Handler handler = new Handler() {
        public void handleMessage(android.os.Message msg) {

            ActivityUtil.start(SplashActivity.this, MainActivity.class);
        }
    };


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initAlphaAnim();

        startLoginActivity();
    }

    /**
     * 启动登陆页面的Activity
     */
    private void startLoginActivity() {
        new Thread() {
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //发送信息更新UI
                handler.sendEmptyMessage(0);
            }
        }.start();
    }

    /**
     * 启动页面的动画
     */
    private void initAlphaAnim() {
        AlphaAnimation alpha = new AlphaAnimation(0.2f, 1.0f);
        alpha.setFillAfter(true);
        alpha.setDuration(2000);
        ImageView iv = (ImageView) findViewById(R.id.iv_hid);
        iv.startAnimation(alpha);
    }

}
