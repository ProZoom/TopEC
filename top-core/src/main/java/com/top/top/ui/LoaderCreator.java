package com.top.top.ui;

import android.content.Context;

import com.wang.avi.AVLoadingIndicatorView;
import com.wang.avi.Indicator;

import java.util.WeakHashMap;

/**
 * 作者：ProZoom
 * 时间：2018/4/22
 * 描述：以缓存方式创建loader
 */
public class LoaderCreator {

    private static final WeakHashMap<String, Indicator> LOADING_MAP = new WeakHashMap<>();


    static AVLoadingIndicatorView creator(String type, Context context) {

        final AVLoadingIndicatorView avLoadingIndicatorView = new AVLoadingIndicatorView(context);

        if (LOADING_MAP.get(type) == null) {
            //
            final Indicator indicator = getIndicator(type);

            LOADING_MAP.put(type, indicator);

        }

        avLoadingIndicatorView.setIndicator(LOADING_MAP.get(type));

        return avLoadingIndicatorView;
    }


    private static Indicator getIndicator(String name) {

        if (name == null || name.isEmpty()) {
            return null;
        }
        final StringBuilder drawableClassName = new StringBuilder();

        if (!name.contains(".")) {
            final String defaultPackageName = AVLoadingIndicatorView.class.getPackage().getName();
            drawableClassName.append(defaultPackageName)
                    .append(".indicators")
                    .append(".");
        }

        drawableClassName.append(name);

        try {
            final Class<?> drawableClass = Class.forName(drawableClassName.toString());

            return (Indicator) drawableClass.newInstance();

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;

        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;

        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;

        }
    }
}
