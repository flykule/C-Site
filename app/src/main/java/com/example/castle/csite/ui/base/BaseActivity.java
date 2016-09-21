package com.example.castle.csite.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.castle.csite.network.api.ApiService;
import com.example.castle.csite.util.DoubleClickExit;
import com.example.castle.csite.util.MyApplication;
import com.example.castle.csite.view.BindLayout;

import java.lang.annotation.Annotation;

public abstract class BaseActivity extends AppCompatActivity {



    /**
     * 复写onCreate,必须加注解标记使用的layout
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Class cls = getClass();
        if (!cls.isAnnotationPresent(BindLayout.class)) return;
        Annotation annotation = cls.getAnnotation(BindLayout.class);
        BindLayout bindLayout = (BindLayout) annotation;
        setContentView(bindLayout.id());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    /**
     * 复写双击退出
     */
    @Override
    public void onBackPressed() {
        if (!DoubleClickExit.check()) {
            toastMsg("再次点击退出");
        } else {
            super.onBackPressed();
        }
    }

    /**
     * toast简便方法
     *
     * @param msg 要显示的消息
     */
    public void toastMsg(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * toast简便方法
     *
     * @param msg 要显示的消息资源id
     */
    public void toastMsg(@StringRes int msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    /**
     * 得到api服务
     * @return api服务类，用做网络请求
     */
    public ApiService getApiService() {
        return ((MyApplication) getApplication()).getService();
    }

    /**
     * 跳转到另外一个活动
     * @param target 目标活动
     */
    public void gotoTarget(Class target) {
        Intent intent = new Intent(this, target);
        startActivity(intent);
    }

    /**
     * 跳转到另外一个活动并且结束当前活动
     * @param target 目标活动
     */
    public void gotoTargetAndFinish(Class target) {
        Intent intent = new Intent(this, target);
        startActivity(intent);
        finish();
    }

}
