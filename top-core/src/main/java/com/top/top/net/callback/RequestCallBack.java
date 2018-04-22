package com.top.top.net.callback;

import android.os.Handler;

import com.top.top.ui.LoaderStyle;
import com.top.top.ui.TopLoader;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


/**
 * 作者：ProZoom
 * 时间：2018/4/21
 * 描述：
 */
public class RequestCallBack implements Callback<String> {


    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    private final IFailure FAILURE;

    private final IError IERROR;

    ///
    private final LoaderStyle LOAD_STYLE;

    //测试Loader
    private static Handler mHandler = new Handler();
    private long DELAY_MEILL = 3000;

    public RequestCallBack(IRequest request, ISuccess success, IFailure failure, IError iError, LoaderStyle load_style) {
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.IERROR = iError;
        this.LOAD_STYLE = load_style;
    }

    @Override
    public void onResponse(Call<String> call, Response<String> response) {
        if (response.isSuccessful()) {
            if (call.isExecuted()) {
                if (SUCCESS != null) {
                    SUCCESS.onSuccess(response.body());
                }
            }
        } else {
            if (IERROR != null) {
                IERROR.onError(response.code(), response.message());
            }
        }

        stopLoader();

    }


    @Override
    public void onFailure(Call<String> call, Throwable t) {

        if (FAILURE != null) {
            FAILURE.onFailure();
        }

        if (REQUEST != null) {
            REQUEST.onRequestEnd();
        }

        stopLoader();

    }

    private void stopLoader() {

        if (LOAD_STYLE != null) {
            mHandler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    TopLoader.stopLoading();
                }
            }, DELAY_MEILL);
        }
    }


}
