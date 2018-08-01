package com.jokyxray.dailywp.detail;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.jokyxray.dailywp.R;
import com.jokyxray.dailywp.glide.GlideApp;
import com.jokyxray.dailywp.widget.zoomview.PhotoView;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    private PhotoView picture;
    private ImageView transition;
    private String name;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if (Build.VERSION.SDK_INT >= 19) {
            getWindow().setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION, WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        picture = findViewById(R.id.daily_img);
        transition = findViewById(R.id.transition);
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        if(!TextUtils.isEmpty(name)){
            File imageFile = new File(getExternalFilesDir("image"),name);
            if(imageFile.exists() && imageFile.length() > 0){
                GlideApp.with(this).load(imageFile).skipMemoryCache(true).into(picture);
                GlideApp.with(this).load(imageFile).skipMemoryCache(true).into(transition);
            }
        }
        picture.setOnViewTapListener((view, x, y) -> {
            if(Math.abs(picture.getScale() - 1) <= 0.1){
                onBackPressed();
            }
        });
        transition.postDelayed(()->transition.setVisibility(View.INVISIBLE),500);
    }

    @Override
    public void onBackPressed() {
        transition.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }
}
