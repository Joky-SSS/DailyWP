package com.jokyxray.dailywp.detail;

import android.Manifest;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.jokyxray.dailywp.R;
import com.jokyxray.dailywp.glide.GlideApp;
import com.jokyxray.dailywp.uitls.Constant;
import com.jokyxray.dailywp.uitls.FileHelper;
import com.jokyxray.dailywp.uitls.PermissionHelper;
import com.jokyxray.dailywp.widget.zoomview.PhotoView;

import java.io.File;

public class DetailActivity extends AppCompatActivity {

    private static final int REQUESTCODE_PERMISSION = 1002;
    private PhotoView picture;
    private ImageView transition;
    private BottomSheetDialog dialog;
    private String name;
    private String title;

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
        findViewById(R.id.iv_menu).setOnClickListener(v -> showBottomDialog());
        Intent intent = getIntent();
        name = intent.getStringExtra("name");
        title = intent.getStringExtra("title");
        if (!TextUtils.isEmpty(name)) {
            File imageFile = new File(getExternalFilesDir("image"), name);
            if (imageFile.exists() && imageFile.length() > 0) {
                GlideApp.with(this).load(imageFile).skipMemoryCache(true).into(picture);
                GlideApp.with(this).load(imageFile).skipMemoryCache(true).into(transition);
            }
        }
        picture.setOnViewTapListener((view, x, y) -> {
            if (Math.abs(picture.getScale() - 1) <= 0.1) {
                onBackPressed();
            }
        });
        picture.setVisibility(View.INVISIBLE);
        transition.postDelayed(() -> {
            transition.setVisibility(View.INVISIBLE);
            picture.setVisibility(View.VISIBLE);
        }, 500);
    }

    private void showBottomDialog() {
        if (dialog == null) {
            dialog = new BottomSheetDialog(this);
            dialog.setContentView(R.layout.picture_bottomsheet);
            dialog.findViewById(R.id.tv_save).setOnClickListener(v -> {
                dialog.dismiss();
                checkPermission();
            });
        }
        dialog.show();
    }

    @TargetApi(Build.VERSION_CODES.M)
    private void checkPermission() {
        if (!PermissionHelper.isGranted(this, Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
            requestPermissions(new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE}, REQUESTCODE_PERMISSION);
        } else {
            saveImage();
        }
    }

    private void saveImage() {
        FileHelper.saveDailyImage(this, Constant.IMAGE_SAVE_PATH + title + ".jpg", getExternalFilesDir("image").getAbsolutePath() + "/" + name);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        for (int i = 0; i < permissions.length; i++) {
            if (permissions[i].equals(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    saveImage();
                } else {
                    Toast.makeText(this, "没有存储权限", Toast.LENGTH_LONG).show();
                }
            }
        }
    }

    @Override
    public void onBackPressed() {
        transition.setVisibility(View.VISIBLE);
        super.onBackPressed();
    }
}
