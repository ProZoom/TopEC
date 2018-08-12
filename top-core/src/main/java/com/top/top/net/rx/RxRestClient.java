package com.top.top.net.rx;

import android.content.Context;

import com.top.top.R;
import com.top.top.net.HttpMethod;
import com.top.top.net.RestClientBuilder;
import com.top.top.net.RestCreator;
import com.top.top.net.RestService;
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

import io.reactivex.Observable;
import okhttp3.HttpUrl;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;

import static com.top.top.net.HttpMethod.GET;
import static com.top.top.net.HttpMethod.POST;


/**
 * 作者：ProZoom
 * 时间：2018/4/21
 * 描述：建造者模式搭建 Retrofit网络请求框架
 */
public class RxRestClient {


    private final String URL;

    private final WeakHashMap<String, Object> PARAMS = RestCreator.getParams();

    //private final IRequest REQUEST;

    //private final ISuccess SUCCESS;

    //private final IFailure FAILURE;

    //private final IError IERROR;

    private final ResponseBody BODY;

    private File mFILE;

    //private final String DOWNLOAD_DIR;

    //private final String NAME;

    //private final String EXTENSION;
    //////

    private LoaderStyle LOADER_STYLE;

    private Context mContext;

    public RxRestClient(Context context,
                        String url,
                        Map<String, Object> params,
                        ResponseBody body,
                        File file,
                        LoaderStyle loaderStyle) {
        this.mContext = context;
        this.URL = url;
       // this.DOWNLOAD_DIR = download_dir;
        //this.NAME = name;
       // this.EXTENSION = extension;
        this.PARAMS.putAll(params);
        //this.REQUEST = request;
        //this.SUCCESS = success;
        //this.FAILURE = failure;
        //this.IERROR = iError;
        this.BODY = body;
        this.mFILE = file;
        this.LOADER_STYLE = loaderStyle;
    }

    public static RxRestClientBuilder builder() {

        return new RxRestClientBuilder();
    }

    private Observable<String> request(HttpMethod method) {

        final RxRestService service = RestCreator.getRxRestService();

        Call<String> call = null;
        Observable<String> observable=null;

      /*  if (REQUEST != null) {
            REQUEST.onRequestStart();
        }*/

        if (LOADER_STYLE != null) {
            TopLoader.showLoading(mContext, LOADER_STYLE);
        }

        switch (method) {
            case GET:
                observable = service.get(URL, PARAMS);
                break;
            case POST:
                observable = service.post(URL, PARAMS);
                break;

            case POST_RAW:
                observable = service.postRaw(URL, BODY);
                break;
            case PUT:
                observable = service.put(URL, PARAMS);
                break;
            case PUT_RAW:
                observable = service.putRaw(URL, BODY);
                break;
            case DELETE:
                observable = service.delete(URL, PARAMS);
                break;

            case UPLOAD:
                final RequestBody responseBody =
                        RequestBody.create(MediaType.parse(MultipartBody.FORM.toString()), mFILE);

                final MultipartBody.Part body =
                        MultipartBody.Part.createFormData("file", mFILE.getName(), responseBody);

                observable = service.upload(URL, body);

                break;
            default:

                break;


        }
                    return observable;

    }

/*    private Callback<String> getRequestCallback() {

        return new RequestCallBack(REQUEST, SUCCESS, FAILURE, IERROR, LOADER_STYLE);
    }*/


    public final Observable<String> get() {
        return request(GET);
    }

    public final Observable<String> post() {

        if (BODY == null) {
            return request(POST);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.POST_RAW);

        }
    }

    public final Observable<String> put() {
        if (BODY == null) {
            return request(HttpMethod.PUT);
        } else {
            if (!PARAMS.isEmpty()) {
                throw new RuntimeException("params must be null");
            }
            return request(HttpMethod.PUT_RAW);

        }
    }

    public final Observable<String> delete() {
        return request(HttpMethod.DELETE);
    }

    public final Observable<String> upload(){
        return request(HttpMethod.UPLOAD);
    }

    public final Observable<ResponseBody> download() {

        Observable<ResponseBody> observable=RestCreator.getRxRestService().download(URL,PARAMS);
        return  observable;
    }
}
