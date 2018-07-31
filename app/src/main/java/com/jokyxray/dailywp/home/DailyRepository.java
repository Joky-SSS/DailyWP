package com.jokyxray.dailywp.home;

import com.jokyxray.dailywp.model.DailyModel;
import com.jokyxray.dailywp.network.APIHelper;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

public class DailyRepository {

    public Observable<DailyModel> getDailyImages(){
        return APIHelper.getApi().getDailyImages().subscribeOn(Schedulers.io());
    }
}
