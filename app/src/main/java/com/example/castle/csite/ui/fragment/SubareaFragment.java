package com.example.castle.csite.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.castle.csite.R;

/**
 * @author 灰太狼
 * @time 2016/9/25  9:29
 * @desc 分区的页面
 */
public class SubareaFragment extends Fragment {

    String names[] = {"直播", "番剧", "动画", "音乐", "舞蹈", "游戏", "科技", "生活",
            "鬼畜", "时尚", "广告", "娱乐", "电影", "电视剧", "游戏中心",};
    private int[] icons = {R.mipmap.ic_category_live,R.mipmap.ic_category_t13, R.mipmap.ic_category_t1,
            R.mipmap.ic_category_t3,R.mipmap.ic_category_t129, R.mipmap.ic_category_t4,
            R.mipmap.ic_category_t36,R.mipmap.ic_category_t160, R.mipmap.ic_category_t119,
            R.mipmap.ic_category_t155,R.mipmap.ic_category_t165, R.mipmap.ic_category_t5,
            R.mipmap.ic_category_t23,R.mipmap.ic_category_t11, R.mipmap.ic_category_game_center,
         };
    private GridView mGv_show;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_subarea, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mGv_show = (GridView) getActivity().findViewById(R.id.gv_show);
        mGv_show.setAdapter(new HomeAdapter());
    }

    class HomeAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return names.length;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            View view = View.inflate(getActivity(), R.layout.home_item, null);

            TextView textView = (TextView) view.findViewById(R.id.tv_name);
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_icon);

            imageView.setImageResource(icons[position]);
            textView.setText(names[position]);
            return view;

        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }


    }
    
}
