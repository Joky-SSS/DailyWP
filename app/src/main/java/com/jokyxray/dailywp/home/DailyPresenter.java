package com.jokyxray.dailywp.home;

public class DailyPresenter implements DailyContract.Presenter {

    DailyContract.View mView;

    public DailyPresenter(DailyContract.View mView) {
        this.mView = mView;
    }

}
