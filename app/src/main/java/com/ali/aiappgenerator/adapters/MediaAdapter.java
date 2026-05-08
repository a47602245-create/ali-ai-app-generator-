package com.ali.aiappgenerator.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.recyclerview.widget.RecyclerView;
import com.ali.aiappgenerator.R;
import com.bumptech.glide.Glide;
import java.util.List;

public class MediaAdapter extends RecyclerView.Adapter<MediaAdapter.MediaViewHolder> {
    private List<String> mediaList;
    private Context context;

    public MediaAdapter(List<String> mediaList, Context context) {
        this.mediaList = mediaList;
        this.context = context;
    }

    @Override
    public MediaViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_media, parent, false);
        return new MediaViewHolder(view);
    }

    @Override
    public void onBindViewHolder(MediaViewHolder holder, int position) {
        String mediaUrl = mediaList.get(position);
        Glide.with(context).load(mediaUrl).into(holder.mediaImageView);
    }

    @Override
    public int getItemCount() {
        return mediaList.size();
    }

    public static class MediaViewHolder extends RecyclerView.ViewHolder {
        ImageView mediaImageView;

        public MediaViewHolder(View itemView) {
            super(itemView);
            mediaImageView = itemView.findViewById(R.id.mediaImageView);
        }
    }
}