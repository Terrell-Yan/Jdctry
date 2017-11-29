package com.example.seanm.jdctry.fragment;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ImageFormat;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;

import com.example.seanm.jdctry.R;
import com.example.seanm.jdctry.adapter.GridAdapter;
import com.example.seanm.jdctry.view.MyGridView;
import com.example.seanm.jdctry.view.ObservableScrollView;

/**
 * Created by SeanM on 2017/10/24.
 */

public class User_F extends Fragment {

//    private int height =20;
    private LinearLayout linearLayout;
    private MyGridView my_gridView_user;
    private GridAdapter adapter_GridView;
    private ObservableScrollView observableScrollView;
    //资源文件
    private int[] pic_path = {R.drawable.user_3, R.drawable.user_4, R.drawable.user_5, R.drawable.user_6, R.drawable.user_7};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.content,container,false);
        initView(view);
        return view;
    }

    private void initView(View view) {
        linearLayout=(LinearLayout)view.findViewById(R.id.lin_include) ;
        my_gridView_user=(MyGridView)view.findViewById(R.id.gridView_user);
        my_gridView_user.setSelector(new ColorDrawable(Color.TRANSPARENT));
        observableScrollView = (ObservableScrollView)view.findViewById(R.id.scrollview);
        adapter_GridView=new GridAdapter(getActivity(), pic_path);
        my_gridView_user.setAdapter(adapter_GridView);
        my_gridView_user.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
                                    long arg3) {


            }
        });
        final int height = linearLayout.getMeasuredHeight();
        observableScrollView.setScrollViewListener(new ObservableScrollView.ScrollViewListener() {
            @Override
            public void onScrollChanged(ObservableScrollView scrollView, int x, int y, int oldx, int oldy) {
                if (y>226){
                Log.d("Log","y----"+y);
                linearLayout.setAlpha(1);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.bgbg));
                }else {
                    float alpha =  0.5f;
                    linearLayout.setAlpha(alpha);
                    linearLayout.setBackgroundColor(getResources().getColor(R.color.bgbg));
                }

            }
        });
    }


}
