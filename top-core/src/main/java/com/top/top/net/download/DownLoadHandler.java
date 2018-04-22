package com.top.top.net.download;

import android.os.Handler;
import android.os.Looper;

import com.top.top.net.RestClient;
import com.top.top.net.RestCreator;
import com.top.top.net.callback.IError;
import com.top.top.net.callback.IFailure;
import com.top.top.net.callback.IRequest;
import com.top.top.net.callback.ISuccess;

import java.util.WeakHashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 作者：ProZoom
 * 时间：2018/4/22
 * 描述：
 */
public class DownLoadHandler extends Handler {


    private final String URL;

    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    private final IFailure FAILURE;

    private final IError IERROR;


    public DownLoadHandler(String url,
                           IRequest request,
                           ISuccess success,
                           IFailure failure,
                           IError iError) {
        this.URL = url;
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.IERROR = iError;
    }

    public void handlerDownload() {

        if(REQUEST!=null){
            REQUEST.onRequestStart();
        }

        RestCreator.getRestService().download(URL,PARAMS)
                .enqueue(new retrofit2.Callback<ResponseBody>() {
                    @Override
                    public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {

                    }

                    @Override
                    public void onFailure(Call<ResponseBody> call, Throwable t) {

                    }
                });

    }
}
