package com.top.topec;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.top.top.Delegate.TopDelegate;
import com.top.top.net.RestClient;
import com.top.top.net.callback.IError;
import com.top.top.net.callback.IFailure;
import com.top.top.net.callback.IRequest;
import com.top.top.net.callback.ISuccess;
import com.top.top.ui.LoaderStyle;
import com.top.topec.launcher.LauncherDelegate;

/**
 * 作者：ProZoom
 * 时间：2018/4/21
 * 描述：
 */
public class ExamlpleDelegate extends TopDelegate {

    private static final String TAG = "ExamlpleDelegate";

    @Override
    public Object setLayout() {
        //return R.layout.activity_main;
        return new LauncherDelegate();
    }

    @Override
    public void onBindView(@Nullable Bundle savedInstanceState, View rootView) {

        testRestClient();
    }

    private void testRestClient() {
        RestClient.builder()
                .url("http://127.0.0.1/index")
                //.params("", "")
                .loader(getContext())
                .onRequest(new IRequest() {
                    @Override
                    public void onRequestStart() {
                    }

                    @Override
                    public void onRequestEnd() {

                    }
                })
                .success(new ISuccess() {
                    @Override
                    public void onSuccess(String response) {
                        Log.i(TAG, response);
                        Toast.makeText(getContext(), response, Toast.LENGTH_SHORT).show();
                    }
                })
                .error(new IError() {
                    @Override
                    public void onError(int code, String message) {
                        Log.i(TAG, message);
                        //Toast.makeText(getContext(), code+"----"+message, Toast.LENGTH_SHORT).show();
                    }
                })
                .failure(new IFailure() {
                    @Override
                    public void onFailure() {
                        Log.i(TAG, "failure");
                        //Toast.makeText(getContext(), "网络请求失败！", Toast.LENGTH_SHORT).show();
                    }
                })
                .build()
                .get();
    }
}
