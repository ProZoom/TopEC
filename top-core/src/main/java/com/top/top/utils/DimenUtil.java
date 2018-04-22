package com.top.top.utils;

import android.content.res.Resources;
import android.util.DisplayMetrics;

import com.top.top.app.Top;

/**
 * 作者：ProZoom
 * 时间：2018/4/22
 * 描述：
 */
public class DimenUtil {

    public static int getScrenWidth(){
        final Resources resources= Top.getApplication().getResources();

        final DisplayMetrics dm=resources.getDisplayMetrics();

        return dm.widthPixels;
    }

     public static int getScrenHeight(){
        final Resources resources= Top.getApplication().getResources();

        final DisplayMetrics dm=resources.getDisplayMetrics();

        return dm.heightPixels;
    }

}
