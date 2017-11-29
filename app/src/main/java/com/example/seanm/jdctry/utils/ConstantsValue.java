package com.example.seanm.jdctry.utils;

import android.os.Environment;

/**
 * Created by SeanM on 2017/10/17.
 */

public class ConstantsValue {
    public static final String COMMON_ACTION="finddreams";
    public static final String IMAGE_PATH_DIR="/.finddreams/img";
    private static final String IMAGE_APP_PATH_DIR="/.finddreams/app_img";
    private static final String APP_LOG_PATH_DIR="/.finddreams/log";
    public static final String IMAGE_APP_PATH= Environment.getExternalStorageState()+IMAGE_APP_PATH_DIR;
    public static final boolean DEBUG=true;
    public static  final String INEENT_URL="url";
    public static final String INENT_TITLE="title";
}
