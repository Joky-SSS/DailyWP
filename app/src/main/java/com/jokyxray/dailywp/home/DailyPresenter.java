package com.jokyxray.dailywp.home;

import android.net.Uri;

import com.jokyxray.dailywp.model.DailyImage;
import com.jokyxray.dailywp.model.DailyModel;
import com.jokyxray.dailywp.network.APIHelper;
import com.jokyxray.dailywp.uitls.Constant;
import com.jokyxray.dailywp.uitls.FileHelper;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class DailyPresenter implements DailyContract.Presenter {

    DailyContract.View mView;
    DailyRepository repository = new DailyRepository();

    public DailyPresenter(DailyContract.View mView) {
        this.mView = mView;
    }

    @Override
    public void loadDailyImage() {
        repository.getDailyImages().flatMap((Function<DailyModel, ObservableSource<DailyImage>>) dailyModel -> Observable.fromIterable(dailyModel.getImages()))
        .doOnNext(dailyImage -> {
            Uri uri = Uri.parse(dailyImage.getCopyrightlink());
            String q = uri.getQueryParameter("q");
            dailyImage.setTitle(q);
            if(!checkImage(dailyImage.getHsh())){
                downloadImage(dailyImage.getUrl(),dailyImage.getHsh());
            }
        }).collect((Callable<List<DailyImage>>) () -> new ArrayList<>(), (imageList, dailyImage) -> imageList.add(dailyImage))
        .subscribe(imageList -> mView.onDailyImageLoad(imageList));
    }

    private boolean checkImage(String hsh) {
        File folder = mView.getActivity().getExternalFilesDir("image");
        return new File(folder,hsh).exists();
    }

    void downloadImage(String url,String hsh){
        APIHelper.getApi().downloadImage(Constant.HOST+url).subscribeOn(Schedulers.io())
                .subscribe(responseBody -> {
                    boolean result = FileHelper.writeResponseBodyToDisk(responseBody,
                            mView.getActivity().getExternalFilesDir("image").getAbsolutePath()+"/"+hsh);
                    if(result) mView.onImageDownloaded();
                });
    }

}
