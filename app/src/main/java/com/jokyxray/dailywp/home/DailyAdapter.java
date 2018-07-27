package com.jokyxray.dailywp.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jokyxray.dailywp.R;
import com.jokyxray.dailywp.model.DailyImage;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ImageHolder> {

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    static class ImageHolder extends RecyclerView.ViewHolder{
        ImageView dailyImg;
        TextView title;
        TextView detail;
        TextView date;
        public ImageHolder(View itemView) {
            super(itemView);
            dailyImg = itemView.findViewById(R.id.daily_img);
            title = itemView.findViewById(R.id.title);
            detail = itemView.findViewById(R.id.copyright);
            date = itemView.findViewById(R.id.date);
        }
    }
}
