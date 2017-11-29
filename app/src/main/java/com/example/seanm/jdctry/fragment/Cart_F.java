package com.example.seanm.jdctry.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.seanm.jdctry.R;
import com.example.seanm.jdctry.adapter.Recy_default_adapter;

import com.example.seanm.jdctry.presenter.BaseFragment;
import com.example.seanm.jdctry.presenter.DaggerHomeFragmentComponent;
import com.example.seanm.jdctry.presenter.HomeContract;
import com.example.seanm.jdctry.presenter.HomeIndex;
import com.example.seanm.jdctry.presenter.HomeMultipleRecycleAdapter;
import com.example.seanm.jdctry.presenter.HomePresenter;
import com.example.seanm.jdctry.presenter.HomePresenterModule;
import com.example.seanm.jdctry.recy.BaseRecycleAdapter;
import com.example.seanm.jdctry.recy.RecyBsViewHolder;
import com.example.seanm.jdctry.recy.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by SeanM on 2017/10/24.
 */

public class Cart_F extends BaseFragment implements HomeContract.View {
    private RefreshLayout recyclerView;
    private List<String> data;
    private Recy_default_adapter recyAdapter;
    private HomeMultipleRecycleAdapter homeMultipleRecycleAdapter;
    private Context context;
    private ImageView img_mine_background;
    private List<HomeIndex.ItemInfoListBean> item = new ArrayList<>();
    @Inject
    HomePresenter mPresenter;

    public void addList() {
        for (int i = 0; i < 6; i++)
            data.add("text" + i);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_f, container, false);
        recyclerView = (RefreshLayout) view.findViewById(R.id.home_recy);
//        img_mine_background
        return view;
    }

    @Override
    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        DaggerHomeFragmentComponent.builder()
                .appComponent(getAppComponent())
                .homePresenterModule(new HomePresenterModule(this))
                .build()
                .inject(this);


        data = new ArrayList<>();

        addList();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyAdapter = new Recy_default_adapter(getActivity(), data, item);
        recyclerView.setAdapter(recyAdapter);
//        mPresenter.getHomeIndexData(1);
        recyclerView.setOnRefreshListner(new RefreshLayout.RefreshListner() {
            @Override
            public void refresh() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        data.clear();
                        addList();
                        recyAdapter.notifyDataSetChanged();
                        recyclerView.setRefreshComplete();
                    }
                }, 2000);


            }

            @Override
            public void loadMore() {
                recyclerView.postDelayed(new Runnable() {
                    @Override
                    public void run() {
//                        mPresenter.getRecommendedWares();
                        recyAdapter.notifyDataSetChanged();
                        recyclerView.setLoadMoreComplete();

                    }
                }, 2000);
            }
        });

    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public void setMoreRecommendedWares(HomeIndex find) {
        Log.i("Log", "HomeIndexData" + find.itemInfoList);
        item.addAll(find.itemInfoList);
    }

    @Override
    public void setHomeIndexData(HomeIndex find) {
        Log.i("Log", "HomeIndexData2" + find);

    }

    @Override
    public void setRecommendedWares(HomeIndex find) {
        Log.i("Log", "HomeIndexData3" + find);
        item.addAll(find.itemInfoList);
    }

    class ThredOne extends Thread {
        @Override
        public void run() {
            super.run();
            for (int i = 0; i < 100; i++) {
                if (i < 0) {
                    int j = i++;
                    for (int itn = 0; itn < i; itn++) {
                        i = itn + j;
                        int x = i * j;
                        x++;
                        j = x - itn;
                        int rigth = 80;
                        double ffff = 0.5;
                        double left = rigth + ffff;
                        double max = 9.1;
                        double min = 0.2;
                        double mon = max * min + rigth - left * (x - j + itn);
                        String position = "mMax+mMin";
                        String extion = "min+person+char";
                    }
                }

            }

        }
    }
}
