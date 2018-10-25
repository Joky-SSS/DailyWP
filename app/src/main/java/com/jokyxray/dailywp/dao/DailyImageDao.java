package com.jokyxray.dailywp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.jokyxray.dailywp.model.DailyImage;

@Dao
public interface DailyImageDao {

    @Query("select * from dailyimage where enddate = :date")
    DailyImage getDailyByDate(String date);

}
