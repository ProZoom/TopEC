package com.top.top.net.interceptor;

import java.io.IOException;
import java.util.LinkedHashMap;

import okhttp3.FormBody;
import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * 作者：ProZoom
 * 时间：2018/4/30
 * 描述：
 */
public abstract class BaseInterceptor implements Interceptor {




    @Override
    public Response intercept(Chain chain) throws IOException {

        return null;
    }

    protected LinkedHashMap<String,String> getUrlParameters(Chain chain){

        final HttpUrl url=chain.request().url();

        int size =url.querySize();

        final LinkedHashMap<String ,String> params=new LinkedHashMap<>();

        for (int i=0;i<size;i++){
            params.put(url.queryParameterName(i),url.queryParameterValue(i));

        }

        return params;

    }


    protected String getUrlParameters(Chain chain,String key){
        final Request request=chain.request();
        return request.url().queryParameter(key);
    }

    protected LinkedHashMap<String,String> getBodyParameters(Chain chain){
        final FormBody formBody = (FormBody) chain.request().body();
        final LinkedHashMap<String,String> param= new LinkedHashMap<>();

        int size=0;
        if(formBody!=null){
            size=formBody.size();
        }
        for (int i=0;i<size;i++){
            param.put(formBody.name(i),formBody.value(i));
        }
        return param;
    }

    protected String getBodyParameters(Chain chain,String key){
        return getBodyParameters(chain).get(key);
    }
}
