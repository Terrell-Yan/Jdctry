package com.example.seanm.jdctry.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.seanm.jdctry.R;
import com.example.seanm.jdctry.presenter.BaseFragment;
import com.example.seanm.jdctry.presenter.DaggerHomeFragmentComponent;
import com.example.seanm.jdctry.presenter.HomeContract;
import com.example.seanm.jdctry.presenter.HomeIndex;
import com.example.seanm.jdctry.presenter.HomePresenter;
import com.example.seanm.jdctry.presenter.HomePresenterModule;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import static com.example.seanm.jdctry.presenter.GlobalAppComponent.getAppComponent;

/**
 * Created by SeanM on 2017/10/24.
 */

public class Tao_F extends BaseFragment {
    private RecyclerView recyclerView;
    private List<String> data;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.tao_f, container, false);
        recyclerView = view.findViewById(R.id.tao_recy);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        data = new ArrayList<>();
    }
}
