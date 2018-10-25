package com.jokyxray.dailywp.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.jokyxray.dailywp.model.DailyImage;

import java.util.List;

@Dao
public interface DailyImageDao {

    @Query("select * from dailyimage where enddate = :date")
    DailyImage getDailyByDate(String date);

    @Query("select * from dailyimage order by sort desc")
    List<DailyImage> getAllDailyImage();

    @Query("select hsh from dailyimage where hsh = :hsh")
    String getHsh(String hsh);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertDailyImage(DailyImage dailyImage);
}
