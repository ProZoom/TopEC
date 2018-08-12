package com.top.topec;

import android.app.Application;

import com.joanzapata.iconify.fonts.FontAwesomeModule;
import com.top.top.app.Top;
import com.top.top.net.interceptor.DebugInterceptor;


/**
 * 作者：ProZoom
 * 时间：2018/4/3
 * 描述：
 */
public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Top.init(this)
                .withApiHost("http://127.0.0.1/")
                .withIcon(new FontAwesomeModule())
                .withInterceptor(new DebugInterceptor("index", R.raw.test))
                .configure();
    }
}
