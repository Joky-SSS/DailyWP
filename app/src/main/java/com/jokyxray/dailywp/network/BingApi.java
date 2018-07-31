package com.jokyxray.dailywp.network;

import com.jokyxray.dailywp.model.DailyModel;

import io.reactivex.Observable;
import okhttp3.ResponseBody;
import retrofit2.http.GET;
import retrofit2.http.Streaming;
import retrofit2.http.Url;

public interface BingApi {

    @GET("HPImageArchive.aspx?format=js&idx=0&n=99&mkt=zh-CN")
    Observable<DailyModel> getDailyImages();

    @Streaming
    @GET
    Observable<ResponseBody> downloadImage(@Url String url);

}
