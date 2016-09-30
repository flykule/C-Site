package com.example.castle.csite.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.Toolbar;
import android.text.ClipboardManager;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.castle.csite.R;
import com.example.castle.csite.ui.base.BaseActivity;
import com.example.castle.csite.view.BindLayout;



/**
 * Created by castle on 16-9-30.
 * 网页界面
 */
@BindLayout(id = R.layout.activity_web)
public class WebActivity extends BaseActivity {
    //网页标题
    public static final String WEB_TITLE = "WEB_TITLE";
    //网页地址
    public static final String WEB_URL = "WEB_URL";

    Toolbar mToolbar;

    WebView mContentWeb;
    private String mTitle;
    private String mUrl;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mToolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDefaultDisplayHomeAsUpEnabled(true);
        mContentWeb = (WebView)findViewById(R.id.content_web);
        Intent intent = getIntent();
        if (intent != null) {
            onNewIntent(intent);
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        mTitle = intent.getStringExtra(WEB_TITLE);
        mUrl = intent.getStringExtra(WEB_URL);
        getSupportActionBar().setTitle(mTitle);
        WebSettings settings = mContentWeb.getSettings();
        settings.setJavaScriptEnabled(true);
        mContentWeb.setWebChromeClient(new WebChromeClient());
        mContentWeb.setWebViewClient(new MyWebClient());
        mContentWeb.loadUrl(mUrl);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.web,menu);
        return super.onCreateOptionsMenu(menu);
    }

    //在WebView中自身打开
    static class MyWebClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            //把事件消费掉
            return true;
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        gotoTargetAndFinish(MainActivity.class);
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_share:
                
                break;
            //在浏览器里面打开
            case R.id.menu_browser:
                Uri uri = Uri.parse(mUrl);
                Intent intent = new Intent(Intent.ACTION_VIEW,uri);
                startActivity(intent);
                break;
            //复制链接
            case R.id.menu_copy:
                // 从API11开始android推荐使用android.content.ClipboardManager
                // 为了兼容低版本我们这里使用旧版的android.text.ClipboardManager，虽然提示deprecated，但不影响使用。
                ClipboardManager cm = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                // 将文本内容放到系统剪贴板里。
                cm.setText(mUrl);
                toastMsg("复制成功");
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
