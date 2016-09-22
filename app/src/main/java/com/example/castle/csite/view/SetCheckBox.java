package com.example.castle.csite.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.castle.csite.R;


/**
 * @author 灰太狼
 * @time 2016/7/24  13:06
 * @desc 自定义控件CheckBox
 */
public class SetCheckBox extends RelativeLayout {

    private CheckBox cb;
//    private boolean checked;
    private TextView tv_set_update;

    public SetCheckBox(Context context) {
        super(context, null);
        initView(context);
    }

    public SetCheckBox(Context context, AttributeSet attrs) {
        super(context, attrs);

        initView(context);
        //获取自定义控件属性中的值 参数1 ac_set中生成的xmlns文件，参数2 设置的属性名
        String bigtitle = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto", "bigtitle");

        tv_set_update.setText(bigtitle);
    }

    private void initView(Context context) {
        //this 表示把当前这个文件挂载在当前这个布局上，
        // 接下来再获取布局文件中的控件时就不需要添加加载的布局了直接find获取就行了
        View.inflate(context, R.layout.ui_set_cb, this);

        tv_set_update = (TextView) findViewById(R.id.tv_set_update);
        cb = (CheckBox) findViewById(R.id.cb_set_update);
    }

    public void setChecked(boolean state) {
        cb.setChecked(state);
    }

    public boolean isChecked() {
        return cb.isChecked();
    }
}
