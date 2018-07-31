package com.jokyxray.dailywp.home;

import android.app.Activity;

import com.jokyxray.dailywp.model.DailyImage;

import java.util.List;

public interface DailyContract {
    interface View{
        Activity getActivity();
        void onDailyImageLoad(List<DailyImage> imageList);
        void onImageDownloaded();
    }

    interface Presenter{
        void loadDailyImage();
    }
}
