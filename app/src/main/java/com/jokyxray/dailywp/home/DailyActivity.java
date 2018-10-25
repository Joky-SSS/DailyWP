package com.jokyxray.dailywp.home;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.jokyxray.dailywp.R;
import com.jokyxray.dailywp.detail.DetailActivity;
import com.jokyxray.dailywp.model.DailyImage;
import com.jokyxray.dailywp.uitls.PermissionHelper;

import java.util.ArrayList;
import java.util.List;

public class DailyActivity extends AppCompatActivity implements DailyContract.View,DailyAdapter.OnItemClickListener{
    static int REQUESTCODE_PERMISSION = 1001;
    DailyPresenter mPresenter;
    Toolbar mToolbar;
    RecyclerView mDailyView;
    DailyAdapter mAdapter;
    List<DailyImage> mImageList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daily);
        mPresenter = new DailyPresenter(this);
        initUI();
        mPresenter.loadDailyImage();
        checkPermission();
    }

    private void initUI() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mDailyView = findViewById(R.id.rv_dailylist);
        mDailyView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new DailyAdapter(mImageList,this);
        mDailyView.setAdapter(mAdapter);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public void onDailyImageLoad(List<DailyImage> imageList) {
        mImageList.clear();
        mImageList.addAll(imageList);
        runOnUiThread(() -> mAdapter.notifyDataSetChanged());
    }

    @Override
    public void onImageDownloaded() {
        runOnUiThread(() -> mAdapter.notifyDataSetChanged());
    }

    @Override
    public void onItemClick(View itemView,int position) {
        DailyImage image = mImageList.get(position);
        View targetView = itemView.findViewById(R.id.daily_img);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("name",image.getHsh());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions compat = ActivityOptions.makeSceneTransitionAnimation(this, targetView, getString(R.string.transitionImage));
            ActivityCompat.startActivity(this, intent, compat.toBundle());
        }else{
            startActivity(intent);
        }
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        if(!PermissionHelper.isGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.READ_EXTERNAL_STORAGE},REQUESTCODE_PERMISSION );
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
