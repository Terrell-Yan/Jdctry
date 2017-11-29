package com.example.seanm.jdctry.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.Context;

import java.util.Stack;

/**
 * Created by SeanM on 2017/10/16.
 */

public class AppManager {
    private static Stack<Activity> activities;
    private static AppManager instance;
    private AppManager(){

    }
    /**
     * 单一实例
     */
    public static  AppManager getAppManager(){
        if (instance==null){
            instance=new AppManager();
        }
        return  instance;
    }
    /**
     * 添加Activity到堆栈中
     */
    public void addActivity(Activity activity){
        if (activities==null){
            activities=new Stack<Activity>();
        }
        activities.add(activity);
    }
    /**
     * 获取当前Activity
     */
    public Activity currentActivity(){
        Activity activity=activities.lastElement();
        return  activity;
    }
    /**
     * 结束当前Activity(堆栈中最后压入的）
     */
    public void  finishActivity(){
        Activity activity=activities.lastElement();
        finishActivity(activity);
    }
    /**
     * 结束指定的Activity
     */
    public void finishActivity (Activity activity){
        if (activity!=null){
            activities.remove(activity);
            activity.finish();
            activity=null;
        }
    }
    /**
     * 结束指定类名的Activity
     */
    public  void finishAcitivity(Class<?>cls){
        for (Activity activity:activities){
            if (activity.getClass().equals(cls)){
                finishActivity(activity);
            }
        }
    }
    /**
     * 结束所有Activity
     */
    public void finishALLActivity(){
        for (int i=0,size=activities.size();i<size;i++){
           if (null!=activities.get(i)){
               activities.get(i).finish();
           }

        }
        activities.clear();
    }
    /**
     * 安全退出
     */
    public  void AppExit(Context context){
        try{
            finishActivity();;
            ActivityManager activityManager=(ActivityManager)context.getSystemService(Context.ACTIVITY_SERVICE);
            activityManager.restartPackage(context.getPackageName());
            System.exit(0);
            android.os.Process.killProcess(android.os.Process.myPid());
        }catch (Exception e){

        }

    }
}
