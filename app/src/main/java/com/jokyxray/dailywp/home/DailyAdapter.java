package com.jokyxray.dailywp.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jokyxray.dailywp.R;
import com.jokyxray.dailywp.glide.GlideApp;
import com.jokyxray.dailywp.model.DailyImage;

import java.util.List;

public class DailyAdapter extends RecyclerView.Adapter<DailyAdapter.ImageHolder> implements View.OnClickListener {

    List<DailyImage> imageList;
    OnItemClickListener listener;

    public DailyAdapter(List<DailyImage> imageList,OnItemClickListener listener) {
        this.imageList = imageList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public ImageHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.daily_item, parent, false);
        v.setOnClickListener(this);
        ImageHolder holder = new ImageHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ImageHolder holder, int position) {
        holder.itemView.setTag(R.id.position_key, position);
        DailyImage image = imageList.get(position);
        holder.title.setText(image.getTitle());
        holder.detail.setText(image.getCopyright());
        holder.date.setText(image.getEnddate());
        GlideApp.with(holder.dailyImg.getContext()).load(holder.dailyImg.getContext().getExternalFilesDir("image").getAbsoluteFile() + "/" + image.getHsh()).into(holder.dailyImg);
    }

    @Override
    public int getItemCount() {
        return imageList == null ? 0 : imageList.size();
    }

    @Override
    public void onClick(View v) {
        if (listener != null)
            listener.onItemClick(v,(Integer) v.getTag(R.id.position_key));
    }

    static class ImageHolder extends RecyclerView.ViewHolder {
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

    interface OnItemClickListener {
        void onItemClick(View itemView, int position);
    }
}
