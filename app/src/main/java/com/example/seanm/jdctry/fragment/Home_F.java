package com.example.seanm.jdctry.fragment;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.example.seanm.jdctry.R;
import com.example.seanm.jdctry.pullrecy.indicator.PtrFrameLayout;
import com.example.seanm.jdctry.pullrecy.indicator.PtrHandler;
import com.example.seanm.jdctry.recy.BaseRecycleAdapter;
import com.example.seanm.jdctry.recy.RecyBsViewHolder;
import com.example.seanm.jdctry.utils.ScreenUtil;
import com.example.seanm.jdctry.view.HeaderRecyView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SeanM on 2017/10/24.
 */

public class Home_F extends Fragment implements HeaderRecyView.RefreshDistanceListener {
    private RecyclerView recyclerView;
    private HeaderRecyView headerRecyView;
    private int distanceY;
    private List<String> data;
    FrameLayout homeTitleBarLayout;
    View homeTitleBarBgView;;
    LinearLayout scanningLayout;
    LinearLayout advisoryLayout;
    private static int DISTANCE_WHEN_TO_SELECTED = 40;
    private BaseRecycleAdapter bap;
    public void addList() {
        for (int i = 0; i < 6; i++)
            data.add("text"+i);

    }
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.act,container,false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        homeTitleBarBgView=view.findViewById(R.id.home_title_bar_bg_view);
        homeTitleBarLayout=view.findViewById(R.id.home_title_bar_layout);
        scanningLayout=view.findViewById(R.id.scanning_layout);
        advisoryLayout=view.findViewById(R.id.advisory_layout);
        headerRecyView=view.findViewById(R.id.rotate_header_list_view_frame);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        data = new ArrayList<>();
        addList();
        initPtrFrame();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                distanceY += dy;
                if (distanceY > ScreenUtil.dip2px(getActivity(), 20)) {
                    homeTitleBarBgView.setBackgroundColor(getResources().getColor(R.color.white));
                    if (Build.VERSION.SDK_INT > 10) {
                        homeTitleBarBgView.setAlpha(distanceY * 1.0f / ScreenUtil.dip2px(getActivity(), 100));
                    }
                    else {
                        DISTANCE_WHEN_TO_SELECTED = 20;
                    }
                }
                else {
                    homeTitleBarBgView.setBackgroundColor(0);
                }

                if (distanceY > ScreenUtil.dip2px(getActivity(), DISTANCE_WHEN_TO_SELECTED) && !scanningLayout.isSelected()) {
                    scanningLayout.setSelected(true);
                    advisoryLayout.setSelected(true);
                }
                else if (distanceY <= ScreenUtil.dip2px(getActivity(), DISTANCE_WHEN_TO_SELECTED) && scanningLayout.isSelected()) {
                    scanningLayout.setSelected(false);
                    advisoryLayout.setSelected(false);
                }
            }
        });
        bap=new BaseRecycleAdapter(getActivity(),R.layout.item,data);
        recyclerView.setAdapter(bap);

        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                addList();
                bap.notifyDataSetChanged();
            }
        },1000);

        bap.setCallBack(new BaseRecycleAdapter.CallBack() {
            @Override
            public <T> void convert(RecyBsViewHolder holder, T bean, int position) {

            }
        });

    }
    /**
     * 初始化下拉刷新
     */
    private void initPtrFrame() {

        headerRecyView.setOnRefreshDistanceListener(this);
        headerRecyView.setPtrHandler(new PtrHandler() {
            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                updateData();
            }
        });

        // 是否进入页面就开始显示刷新动作
        /*mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPtrFrame.autoRefresh();
            }
        }, 100);*/
    }

    /**
     * 下拉后刷新数据
     */
    private void updateData() {
        headerRecyView.postDelayed(new Runnable() {
            @Override
            public void run() {
                addList();
               bap.notifyDataSetChanged();
            }
        }, 1000);

    }

    @Override
    public void onPositionChange(int currentPosY) {
        if (currentPosY > 0) {
            if (homeTitleBarLayout.getVisibility() == View.VISIBLE) {
                homeTitleBarLayout.setVisibility(View.GONE);
            }
        } else {
            if (homeTitleBarLayout.getVisibility() == View.GONE) {
                homeTitleBarLayout.setVisibility(View.VISIBLE);
                distanceY = 0;
            }
        }

    }
    class ThredOne extends Thread {
        @Override
        public void run() {
            super.run();


        }
    }
}
