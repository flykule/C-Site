package com.example.castle.csite.ui.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.castle.csite.R;


/**
 * @author 吴志强
 * @time 2016/8/13  20:21
 * @desc ${TODD}
 */
public class CoinFragment extends Fragment {
	@Nullable
	@Override
	public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_b,container,false);
	}
}
