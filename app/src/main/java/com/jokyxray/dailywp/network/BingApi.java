package com.jokyxray.dailywp.network;

import com.jokyxray.dailywp.model.DailyImage;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface BingApi {

    @GET("HPImageArchive.aspx?format=js&idx=0&n=9&mkt=zh-CN")
    Observable<List<DailyImage>> getDailyImages();


}
