package com.example.seanm.jdctry.myApp;

import android.app.Activity;
import android.app.Application;

import com.example.seanm.jdctry.presenter.GlobalAppComponent;
import com.example.seanm.jdctry.utils.ConstantsValue;
import com.example.seanm.jdctry.utils.CrashHandler;

import java.util.LinkedList;

/**
 * Created by SeanM on 2017/10/17.
 */

public class MyApplication extends Application{
    private static final String TAG="InitApplication";
    private static boolean isLogged=false;
    private static MyApplication instance;
    private static LinkedList<Activity> activities;
    private Activity activity;

    @Override
    public void onCreate() {
        instance=this;

        if (!ConstantsValue.DEBUG) {
            CrashHandler crashHandler=new CrashHandler(this);
            Thread.setDefaultUncaughtExceptionHandler(crashHandler);
        }
        activities=new LinkedList<>();
        super.onCreate();
        GlobalAppComponent.init(this);
    }
    public Activity getActivity(){
        return activity;
    }
    public static MyApplication getInstance()
    {
        return instance;
    }
    public void setActivity(Activity activity){
        this.activity=activity;
    }
    public void removeActivity(Activity a){
        activities.remove(a);
    }public void finishActivity(){
        for (Activity activity:activities){
            activity.finish();
        }
        activities.clear();
        //杀死该应用进程
        android.os.Process.killProcess(android.os.Process.myPid());
    }

}
