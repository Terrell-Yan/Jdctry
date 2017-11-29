package com.example.seanm.jdctry.utils;

import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by SeanM on 2017/10/17.
 */

public class FileUtil {
    public static final String ROOT= Environment.getExternalStorageDirectory().getPath()+"/nicehair";
    public static final String CAMERA = Environment.getExternalStorageDirectory().getPath() + "/DCIM/Camera/";
    public static final String CACHE_IMG ="/cache/images/";
    /**
     * 应用日志目录文件
     */
    public static String APP_LOG_PATH=ROOT+"log/";
    public static String LOGFILE=APP_LOG_PATH+"log.txt";
    /**
     * 读取输入入流数据
     */
    public static byte[] read(InputStream inputStream)throws  Exception{
        ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
        byte[] buffer=new byte[1024];
        int len=0;
        while((len=inputStream.read(buffer))!=-1){
            outputStream.write(buffer,0,len);
        }
        inputStream.close();
        return outputStream.toByteArray();
    }
    /**
     * 判断sd是否可以
     */
    public static boolean isSdcarExist(){
        if (Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED
        )){
            return true;
        }
        return false;
    }
    /**
     * 创建根目录
     */
    public static void createDirFile(String path){
        File dir=new File(path);
        if (!dir.exists()){
            dir.mkdir();
        }
    }
    /**
     * 创建文件
     */
    public static File createNewFile(String path){
        File file=new File(path);
        if (!file.exists()){
            try {
                file.createNewFile();
            }catch (IOException e){
                return null;
            }
        }
        return file;
    }
}
