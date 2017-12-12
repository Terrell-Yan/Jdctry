package com.example.seanm.jdctry.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seanm.jdctry.R;
import com.example.seanm.jdctry.adapter.Recy_default_adapter;
import com.example.seanm.jdctry.presenter.BaseFragment;
import com.example.seanm.jdctry.presenter.DaggerHomeFragmentComponent;
import com.example.seanm.jdctry.presenter.HomeContract;
import com.example.seanm.jdctry.presenter.HomeIndex;
import com.example.seanm.jdctry.presenter.HomePresenter;
import com.example.seanm.jdctry.presenter.HomePresenterModule;
import com.example.seanm.jdctry.recy.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.seanm.jdctry.presenter.GlobalAppComponent.getAppComponent;

/**
 * Created by SeanM on 2017/10/24.
 */

public class Tao_F extends BaseFragment {



    private RefreshLayout recyclerView;
    private List<String> data;
    private Recy_default_adapter recyAdapter;


    public void addList() {
        for (int i = 0; i < 6; i++)
            data.add("text" + i);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tao_f, container, false);
        recyclerView = view.findViewById(R.id.home_recy);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<>();

        addList();
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyAdapter = new Recy_default_adapter(getActivity(), data);
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

                        recyclerView.setLoadMoreComplete();

                    }
                }, 2000);
            }
        });



    }
    private void addList2(){


    }
}
