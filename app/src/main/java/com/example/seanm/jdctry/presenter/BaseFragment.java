package com.example.seanm.jdctry.presenter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.support.v4.app.Fragment;
import android.widget.Toast;


/**
 * Created by admin on 2017/3/15.
 */

public class BaseFragment extends Fragment {
    protected Activity mActivity;

    protected Context mContext;
    /**
     * gif_logo进度dialog
     */
    private Dialog dialog;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mActivity = activity;
        mContext = getAppComponent().getContext();
    }

    protected void showShortToast(String message){
        Toast.makeText(mActivity.getApplicationContext(),message, Toast.LENGTH_SHORT).show();
    }



    protected AppComponent getAppComponent() {
        return GlobalAppComponent.getAppComponent();
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}
