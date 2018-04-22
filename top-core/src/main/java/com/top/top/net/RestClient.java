package com.top.top.net;

import android.content.Context;

import com.top.top.net.callback.IError;
import com.top.top.net.callback.IFailure;
import com.top.top.net.callback.IRequest;
import com.top.top.net.callback.ISuccess;
import com.top.top.net.callback.RequestCallBack;
import com.top.top.net.download.DownLoadHandler;
import com.top.top.ui.LoaderStyle;
import com.top.top.ui.TopLoader;

import java.io.File;
import java.util.Map;
import java.util.WeakHashMap;

import okhttp3.MediaType;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

/**
 * 作者：ProZoom
 * 时间：2018/4/21
 * 描述：建造者模式搭建 Retrofit网络请求框架
 */
public class RestClient {


    private final String URL;

    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    private final IRequest REQUEST;

    private final ISuccess SUCCESS;

    private final IFailure FAILURE;

    private final IError IERROR;

    private final ResponseBody BODY;

    private File mFILE;

    private final String DOWNLOAD_DIR;

    private final String NAME;

    private final String EXTENSION;
    //////

    private LoaderStyle LOADER_STYLE;

    private Context mContext;

    public RestClient(Context context,
                      String url,
                      Map<String, Object> params,
                      IRequest request,
                      ISuccess success,
                      IFailure failure,
                      IError iError,
                      ResponseBody body,
                      File file,
                      String download_dir,
                      String name,
                      String extension,
                      LoaderStyle loaderStyle) {
        this.mContext = context;
        this.URL = url;
        this.DOWNLOAD_DIR = download_dir;
        this.NAME = name;
        this.EXTENSION = extension;
        this.PARAMS.putAll(params);
        this.REQUEST = request;
        this.SUCCESS = success;
        this.FAILURE = failure;
        this.IERROR = iError;
        this.BODY = body;
        this.mFILE = file;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RestClientBuilder builder() {

        return new RestClientBuilder();
    }

    private void request(HttpMethod method) {

        final RestService service = RestCreator.getRestService();

        Call<String> call = null;

        if (REQUEST != null) {
            REQUEST.onRequestStart();
        }

        if (LOADER_STYLE != null) {
            TopLoader.showLoading(mContext, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                call = service.get(URL, PARAMS);
                break;
            case POST:
                call = service.post(URL, PARAMS);
                break;

            case POST_RAW:
                call = service.postRaw(URL, BODY);
                break;
            case PUT:
                call = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                call = service.putRaw(URL, BODY);
                break;
            case DELETE:
                call = service.delete(URL, PARAMS);
                break;

            case UPLOAD:
                final RequestBody responseBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), mFILE);

                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", mFILE.getName(), responseBody);

                call = RestCreator.getRestService().upload(URL, body);

                break;
            default:

                break;


        }

        if (call != null) {
            call.enqueue(getRequestCallback());
        }

    }

    private Callback<String> getRequestCallback() {

        return new RequestCallBack(REQUEST, SUCCESS, FAILURE, IERROR, LOADER_STYLE);
    }


    public final void get() {
        request(HttpMethod.GET);
    }

    public final void post() {

        if (BODY == null) {
            request(HttpMethod.POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.POST_RAW);

        }
    }

    public final void put() {
        if (BODY == null) {
            request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            request(HttpMethod.PUT_RAW);

        }
    }

    public final void delete() {
        request(HttpMethod.DELETE);
    }


    public final void download() {
        new DownLoadHandler(URL, REQUEST, SUCCESS, FAILURE, IERROR)
                .handlerDownload();
    }
}
