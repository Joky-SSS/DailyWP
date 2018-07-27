package com.jokyxray.dailywp.home;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import com.jokyxray.dailywp.R;

public class DailyActivity extends AppCompatActivity implements DailyContract.View{

    DailyPresenter mPresenter;
    Toolbar mToolbar;
    RecyclerView mDailyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        mPresenter = new DailyPresenter(this);
        initUI();
    }

    private void initUI() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDailyView = findViewById(R.id.rv_dailylist);

    }
}
