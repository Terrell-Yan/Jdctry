package com.example.seanm.jdctry.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.view.Window;

import com.example.seanm.jdctry.http.MyHttpClient;
import com.example.seanm.jdctry.utils.AppManager;
import com.loopj.android.http.AsyncHttpClient;

/**
 * Created by SeanM on 2017/10/16.
 */

public abstract class BaseActivity extends FragmentActivity {
    protected Context context;
    protected AsyncHttpClient httpClient;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        AppManager.getAppManager().addActivity(this);
        context=this;
        httpClient= MyHttpClient.getHttpClient();
        initView();
        initData();
    }
    protected abstract void initView();
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        AppManager.getAppManager().finishActivity(this);
    }
    private static  int  focutionN(Integer[] srcArray,int des){
        int low=0;
        int high=srcArray.length-1;
        while((low<=high)&&(low<=srcArray.length-1)&&(high<=srcArray.length-1)){
            //计算出中间索引值
            int milddle=(high+low)/2;
            if (des==srcArray[milddle]){
                return milddle;
            }else if(des<srcArray[milddle]){
                 high= milddle-1;
            }else {
               low= milddle+1;
            }
        }
        return -1;
    }
}
