package com.top.topec;

import android.widget.Toast;

import com.top.top.Delegate.TopDelegate;
import com.top.top.activity.ProxyActivity;
import com.top.top.net.RestClient;
import com.top.top.net.callback.IError;
import com.top.top.net.callback.IFailure;
import com.top.top.net.callback.IRequest;
import com.top.top.net.callback.ISuccess;

/**
 * 作者：ProZoom
 * 时间：2018/4/21
 * 描述：
 */
public class MainActivity extends ProxyActivity {
    @Override
    public TopDelegate setRootDelegate() {
        return new ExamlpleDelegate();
    }



}