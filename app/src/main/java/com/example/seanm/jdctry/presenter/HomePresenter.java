package com.example.seanm.jdctry.presenter;



import android.util.Log;

import javax.inject.Inject;

import io.reactivex.observers.DisposableObserver;

/**
 * @author admin
 */

public class HomePresenter extends BasePresenter implements HomeContract.Presenter{
    private DataManager mDataManager;

    private HomeContract.View mHomeView;
    @Inject
    public HomePresenter(DataManager mDataManager, HomeContract.View view) {
        this.mDataManager = mDataManager;
        this.mHomeView = view;

    }

    @Override
    public void getHomeIndexData(int flag) {
        addDisposabe(mDataManager.getData(new DisposableObserver<HomeIndex>() {
            @Override
            public void onNext(HomeIndex homeIndex) {
                Log.i("Log","执行了");
                mHomeView.setHomeIndexData(homeIndex);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        },HomeIndex.class, flag == 1 ?"homeindex.txt" : "homeindexevent.txt"));
    }



    @Override
    public void getRecommendedWares() {
        addDisposabe(mDataManager.getData(new DisposableObserver<HomeIndex>() {
            @Override
            public void onNext(HomeIndex homeIndex) {
                mHomeView.setRecommendedWares(homeIndex);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        },HomeIndex.class, "recommend.txt"));
    }

    @Override
    public void getMoreRecommendedWares() {
        addDisposabe(mDataManager.getData(new DisposableObserver<HomeIndex>() {
            @Override
            public void onNext(HomeIndex homeIndex) {
                mHomeView.setMoreRecommendedWares(homeIndex);
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        },HomeIndex.class, "recommended.txt"));
    }
}
