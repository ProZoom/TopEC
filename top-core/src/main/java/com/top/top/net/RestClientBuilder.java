package com.top.top.net;

import android.content.Context;

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
public class RestClientBuilder {


    private String mURL = null;

    private static final Map<String, Object> mParams = RestCreator.getParams();

    private IRequest mRequest = null;

    private ISuccess mSuccess = null;

    private IFailure mFailure = null;

    private IError mError = null;

    private ResponseBody mBody = null;

    private File mFile = null;


    private String DOWNLOAD_DIR = null;

    private String NAME = null;

    private String EXTENSION = null;

    ///
    private Context mContext = null;

    private LoaderStyle mLoaderStyle = null;


    public RestClientBuilder() {
    }

    public final RestClientBuilder url(String url) {

        this.mURL = url;
        return this;
    }

    public final RestClientBuilder params(WeakHashMap<String, Object> params) {
        mParams.putAll(params);
        ;
        return this;
    }

    public final RestClientBuilder params(String key, Object value) {

        mParams.put(key, value);
        return this;
    }


    public final RestClientBuilder raw(String raw) {

        this.mBody = ResponseBody.create(MediaType.parse("application/json;charset=UTF-8"), raw);

        return this;
    }

    public final RestClientBuilder success(ISuccess iSuccess) {
        this.mSuccess = iSuccess;
        return this;
    }

    public final RestClientBuilder failure(IFailure iFailure) {

        this.mFailure = iFailure;
        return this;
    }

    public final RestClientBuilder error(IError iError) {

        this.mError = iError;
        return this;
    }

    public final RestClientBuilder onRequest(IRequest iRequest) {

        this.mRequest = iRequest;
        return this;
    }


    public final RestClientBuilder loader(Context context, LoaderStyle style) {
        this.mContext = context;
        this.mLoaderStyle = style;
        return this;
    }

    public final RestClientBuilder loader(Context context) {
        this.mContext = context;
        this.mLoaderStyle = LoaderStyle.BallClipRotatePulseIndicator;
        return this;
    }

    public final RestClientBuilder file(File file) {
        this.mFile = file;
        return this;
    }

    public final RestClientBuilder file(String file) {
        this.mFile = new File(file);
        return this;
    }


    public final RestClientBuilder dir(String dir) {
        this.DOWNLOAD_DIR = dir;
        return this;
    }

    public final RestClientBuilder extension(String extension) {
        this.EXTENSION = extension;
        return this;
    }

    public final RestClientBuilder name(String name) {
        this.NAME = name;
        return this;
    }

  /*  private Map<String ,Object> checkParams(){
        if(mParams==null){
            return new WeakHashMap<>();
        }
        return mParams;
    }*/

    public final RestClient build() {
        return new RestClient(mContext, mURL, mParams, mRequest, mSuccess, mFailure, mError, mBody, mFile, DOWNLOAD_DIR, NAME, EXTENSION, mLoaderStyle);
    }
}
