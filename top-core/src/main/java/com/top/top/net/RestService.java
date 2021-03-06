package com.top.top.net;


import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

/**
 * 作者：ProZoom
 * 时间：2018/4/21
 * 描述：
 */
public interface RestService {


    @GET
    Call<String> get(@Url String url, @QueryMap Map<String,Object> params);


    @FormUrlEncoded
    @POST
    Call<String> post(@Url String url, @FieldMap Map<String,Object> params);

    @POST
    Call<String> postRaw(@Url String url, @Body ResponseBody body);

    @FormUrlEncoded
    @PUT
    Call<String> put(@Url String url,@FieldMap Map<String ,Object> params);

    @PUT
    Call<String> putRaw(@Url String url,@Body ResponseBody body);

    @DELETE
    Call<String> delete(@Url String url,@QueryMap Map<String,Object> params);



    @Streaming
    @GET
    Call<ResponseBody> download(@Url String url,@QueryMap Map<String,Object> params);


    @Multipart
    @POST
    Call<String> upload(@Url String url, @Part MultipartBody.Part file);


}
