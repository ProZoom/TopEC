package com.top.top.net.rx;

import android.content.Context;

import com.top.top.net.RestClient;
import com.top.top.net.RestCreator;
import com.top.top.net.callback.IError;
import com.top.top.net.callback.IFailure;
import com.top.top.net.callback.IRequest;
import com.top.top.net.callback.ISuccess;
import com.top.top.ui.LoaderStyle;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;
import okhttp3.ResponseBody;

/**
 * 作者：ProZoom
 * 时间：2018/4/21
 * 描述：
 */
public class RxRestClientBuilder {


    private String mURL = null;

    private static final Map<String, Object> mParams = RestCreator.getParams();

/*    private IRequest mRequest = null;

    private ISuccess mSuccess = null;

    private IFailure mFailure = null;

    private IError mError = null;*/

    private ResponseBody mBody = null;

    private File mFile = null;

/*
    private String DOWNLOAD_DIR = null;

    private String NAME = null;

    private String EXTENSION = null;*/

    ///
    private Context mContext = null;

    private LoaderStyle mLoaderStyle = null;


    public RxRestClientBuilder() {
    }

    public final RxRestClientBuilder url(String url) {

        this.mURL = url;
        return this;
    }

    public final RxRestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        ;
        return this;
    }

    public final RxRestClientBuilder params(String key, Object value) {

        mParams.put(key, value);
        return this;
    }


    public final RxRestClientBuilder raw(String raw) {

        this.mBody = ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);

        return this;
    }

    /*public final RxRestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RxRestClientBuilder failure(IFailure iFailure) {

        this.mFailure = iFailure;
        return this;
    }

    public final RxRestClientBuilder error(IError iError) {

        this.mError = iError;
        return this;
    }

    public final RxRestClientBuilder onRequest(IRequest iRequest) {

        this.mRequest = iRequest;
        return this;
    }*/


    public final RxRestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RxRestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RxRestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RxRestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }


    /*public final RxRestClientBuilder dir(String dir) {
        this.DOWNLOAD_DIR = dir;
        return this;
    }

    public final RxRestClientBuilder extension(String extension) {
        this.EXTENSION = extension;
        return this;
    }

    public final RxRestClientBuilder name(String name) {
        this.NAME = name;
        return this;
    }*/

  /*  private Map<String ,Object> checkParams(){
        if(mParams==null){
            return new WeakHashMap<>();
        }
        return mParams;
    }*/

    public final RxRestClient build() {
        return new RxRestClient(mContext, mURL, mParams,mBody, mFile, mLoaderStyle);
    }
}
