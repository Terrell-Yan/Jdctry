package com.example.seanm.jdctry.fragment;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
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
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.seanm.jdctry.R;
import com.example.seanm.jdctry.adapter.Recy_default_adapter;

import com.example.seanm.jdctry.presenter.BaseFragment;
import com.example.seanm.jdctry.presenter.BaseQuickAdapter;
import com.example.seanm.jdctry.presenter.DaggerHomeFragmentComponent;
import com.example.seanm.jdctry.presenter.HomeContract;
import com.example.seanm.jdctry.presenter.HomeIndex;
import com.example.seanm.jdctry.presenter.HomeMultipleRecycleAdapter;
import com.example.seanm.jdctry.presenter.HomePresenter;
import com.example.seanm.jdctry.presenter.HomePresenterModule;
import com.example.seanm.jdctry.pullrecy.indicator.PtrFrameLayout;
import com.example.seanm.jdctry.pullrecy.indicator.PtrHandler;
import com.example.seanm.jdctry.recy.BaseRecycleAdapter;
import com.example.seanm.jdctry.recy.RecyBsViewHolder;
import com.example.seanm.jdctry.recy.RefreshLayout;
import com.example.seanm.jdctry.utils.ScreenUtil;
import com.example.seanm.jdctry.view.HeaderRecyView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by SeanM on 2017/10/24.
 */

public class Cart_F extends BaseFragment  implements HeaderRecyView.RefreshDistanceListener ,HomeContract.View, BaseQuickAdapter.RequestLoadMoreListener  {
    private static int DISTANCE_WHEN_TO_SELECTED = 40;
    private HeaderRecyView mPtrFrame;
    private int distanceY;
    View homeTitleBarBgView;
    LinearLayout scanningLayout, advisoryLayout;
    FrameLayout homeTitleBarLayout;
    private RecyclerView recyclerView;
    private List<String> data;
    private Recy_default_adapter recyAdapter;
    private HomeMultipleRecycleAdapter homeMultipleRecycleAdapter;
    private Context context;
    private ImageView img_mine_background;
    private List<HomeIndex.ItemInfoListBean> item = new ArrayList<>();
    private View rootView = null;
    @Inject
    HomePresenter mPresenter;
    private int flag = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.home_f, container, false);
        recyclerView = (RecyclerView) view.findViewById(R.id.recy_hoe);
        scanningLayout = view.findViewById(R.id.scanning_layout);
        advisoryLayout = view.findViewById(R.id.advisory_layout);
        homeTitleBarLayout = view.findViewById(R.id.home_title_bar_layout);
        homeTitleBarBgView = view.findViewById(R.id.home_title_bar_bg_view);
        mPtrFrame = (HeaderRecyView) view.findViewById(R.id.rotate_header_list_view_frame);
        initBase();
        return view;
    }

    /**
     * 初始化下拉刷新及滚动距离title发生的改变
     */
    private void initBase() {
        DaggerHomeFragmentComponent.builder()
                .appComponent(getAppComponent())
                .homePresenterModule(new HomePresenterModule(this))
                .build()
                .inject(this);

        initPtrFrame();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(recyclerView.getContext(), 4, GridLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(gridLayoutManager);
        recyclerView.addItemDecoration(new SpaceItemDecoration(ScreenUtil.dip2px(getContext(), 3)));
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                distanceY += dy;
                if (distanceY > ScreenUtil.dip2px(mActivity, 20)) {
                    homeTitleBarBgView.setBackgroundColor(getResources().getColor(R.color.white));
                    if (Build.VERSION.SDK_INT > 10) {
                        homeTitleBarBgView.setAlpha(distanceY * 1.0f / ScreenUtil.dip2px(mActivity, 100));
                    } else {
                        DISTANCE_WHEN_TO_SELECTED = 20;
                    }
                } else {
                    homeTitleBarBgView.setBackgroundColor(0);
                }

                if (distanceY > ScreenUtil.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED) && !scanningLayout.isSelected()) {
                    scanningLayout.setSelected(true);
                    advisoryLayout.setSelected(true);
                } else if (distanceY <= ScreenUtil.dip2px(mActivity, DISTANCE_WHEN_TO_SELECTED) && scanningLayout.isSelected()) {
                    scanningLayout.setSelected(false);
                    advisoryLayout.setSelected(false);
                }
            }
        });
        homeMultipleRecycleAdapter = new HomeMultipleRecycleAdapter();
        /*recyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                switch (newState){
                    //滑动停止
                    case RecyclerView.SCROLL_STATE_IDLE:

                        break;
                    //手指触摸屏幕停止或拖动时
                    case RecyclerView.SCROLL_STATE_DRAGGING:

                        break;
                    //滑动且手指离开屏幕
                    case RecyclerView.SCROLL_STATE_SETTLING:

                        break;
                }

            }
        });*/

        /*recyclerView.setOnFlingListener(new RecyclerView.OnFlingListener() {
            @Override
            public boolean onFling(int velocityX, int velocityY) {
                if(Math.abs(velocityY) > 5000){

                }
                return false;
            }
        });*/
        homeMultipleRecycleAdapter.setOnLoadMoreListener(this);
        homeMultipleRecycleAdapter.setEnableLoadMore(true);

        recyclerView.setAdapter(homeMultipleRecycleAdapter);
        mPresenter.getHomeIndexData(flag);
        flag = 0;
    }

    /**
     * 初始化下拉刷新
     */
    private void initPtrFrame() {

        mPtrFrame.setOnRefreshDistanceListener(this);
        mPtrFrame.setPtrHandler(new PtrHandler() {
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

//    @Override
//    public void onViewCreated(final View view, @Nullable Bundle savedInstanceState) {
//        super.onViewCreated(view, savedInstanceState);
//        DaggerHomeFragmentComponent.builder()
//                .appComponent(getAppComponent())
//                .homePresenterModule(new HomePresenterModule(this))
//                .build()
//                .inject(this);
//
//
//        data = new ArrayList<>();
//
//
//
//        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
//        recyclerView.setLayoutManager(layoutManager);
//        recyAdapter = new Recy_default_adapter(getActivity(), data);
//        homeMultipleRecycleAdapter = new HomeMultipleRecycleAdapter();
//        recyclerView.setAdapter(homeMultipleRecycleAdapter);
//
//
//    }



    /**
     * 下拉后刷新数据
     */
    private void updateData() {
        mPtrFrame.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPresenter.getHomeIndexData(flag);
                if(flag == 0){
                    flag = 1;
                }
                else{
                    flag = 0;
                }
            }
        }, 1000);
    }
    private HomeIndex.ItemInfoListBean homeBean;

    @Override
    public void setMoreRecommendedWares(HomeIndex find) {
        homeMultipleRecycleAdapter.getData().addAll(find.itemInfoList);
        homeMultipleRecycleAdapter.loadMoreComplete();
    }

    @Override
    public void setHomeIndexData(HomeIndex find) {
        homeMultipleRecycleAdapter.getData().clear();
        homeMultipleRecycleAdapter.resetMaxHasLoadPosition();
        homeMultipleRecycleAdapter.setNewData(find.itemInfoList);
        mPtrFrame.refreshComplete();
    }

    @Override
    public void setRecommendedWares(HomeIndex find) {
        homeMultipleRecycleAdapter.getData().addAll(find.itemInfoList);
        homeMultipleRecycleAdapter.loadMoreComplete();
    }
    @Override
    public void onLoadMoreRequested() {
        recyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (homeMultipleRecycleAdapter.getData().size() >= 90){
                    homeMultipleRecycleAdapter.loadMoreEnd(false);
                }
                else{
                    mPresenter.getRecommendedWares();
                }
            }
        },1000);
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
}
