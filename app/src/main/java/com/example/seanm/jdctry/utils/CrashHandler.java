package com.example.seanm.jdctry.utils;

import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

import com.example.seanm.jdctry.myApp.MyApplication;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**异常崩溃处理类
 * Created by SeanM on 2017/10/17.
 */

public class CrashHandler implements Thread.UncaughtExceptionHandler {
    /**
     * 错误日志文件名称
     */
    static final String LOG_NAME="/crash.txt";
    /**
     * 系统默认的UncaughtException
     */
    private Thread.UncaughtExceptionHandler mDefaultHandler;
    MyApplication myApplication;

public CrashHandler(MyApplication application){
    mDefaultHandler=Thread.getDefaultUncaughtExceptionHandler();
    Thread.setDefaultUncaughtExceptionHandler(this);
    this.myApplication=application;
}


    /**
     *当UncaughtException发生时转入该函数来处理
     * @param t
     * @param e
     */
    @Override
    public void uncaughtException(Thread t, Throwable e) {
       if (!handleException(e)&&mDefaultHandler!=null){
           mDefaultHandler.uncaughtException(t,e);
       }else {
           //等待后台程序结束
           try{
               Log.i(LOG_NAME,"exit start");
               Thread.sleep(3000);
               android.os.Process.killProcess(android.os.Process.myPid());
               System.exit(10);

           }catch (InterruptedException el){
               e.printStackTrace();
           }

       }
    }
    private boolean handleException(final Throwable ex){
        if (ex==null){
            return true;
        }
        ex.printStackTrace();
        new Thread(){
            @Override
            public void run() {
                super.run();
                Looper.prepare();
                Toast.makeText(myApplication.getApplicationContext(), "应用发生异常，即将退出！", Toast.LENGTH_LONG).show();
                Looper.loop();
            }
        }.start();
        //保存错误报告文件
        saveCrashInfoToFile(ex);
        return true;
    }
    private void saveCrashInfoToFile(Throwable ex){
        final StackTraceElement []stack=ex.getStackTrace();
        final String message=ex.getMessage();
        File logFile=new File(FileUtil.APP_LOG_PATH+LOG_NAME);
        if (!logFile.getParentFile().exists())
        {
            logFile.getParentFile().mkdirs();
        }
        FileWriter fw =null;
        final String lineFeed="\r\n";
        try{
            fw=new FileWriter(logFile,true);
//            fw.write();
        }catch (IOException e){
            e.printStackTrace();
        }


    }
}
