package com.example.castle.csite.view;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;

import com.example.castle.csite.R;

import static com.umeng.socialize.utils.DeviceConfig.context;

/**
 * Created by castle on 16-10-1.
 * 自定义推荐内容View
 */
public class RecommendContentView extends FrameLayout {

    FrameLayout mHeaderView;
    RecyclerView mGroupImageRecycler;
    FrameLayout mFooterView;

    private Context mContext;

    public RecyclerView.Adapter getAdapter() {
        return mAdapter;
    }

    public void setAdapter(RecyclerView.Adapter adapter) {
        mAdapter = adapter;
        if (adapter != null) {
            mGroupImageRecycler.setAdapter(adapter);
            mGroupImageRecycler.setLayoutManager(new GridLayoutManager(mContext,2));
        }
    }

    public void setHeaderView(View view) {
        mHeaderView.removeAllViews();
        mHeaderView.addView(view);
        mHeaderView.setVisibility(VISIBLE);
    }

    public void setFooterView(View view) {
        mFooterView.removeAllViews();
        mFooterView.addView(view);
        mFooterView.setVisibility(VISIBLE);
    }

    private RecyclerView.Adapter mAdapter;

    public RecommendContentView(Context context) {
        super(context);
        mContext = context;
    }

    public RecommendContentView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }

    public RecommendContentView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public RecommendContentView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        mContext = context;
    }

    @Override
    protected void onFinishInflate() {
        super.onFinishInflate();
        View view = LayoutInflater.from(context).inflate(R.layout.recommend_content_layout, null);
        mHeaderView = (FrameLayout)view.findViewById(R.id.header_view);
        mGroupImageRecycler = (RecyclerView)view.findViewById(R.id.group_image_recycler);
        mFooterView = (FrameLayout)view.findViewById(R.id.footer_view);
        addView(view);
    }


}
