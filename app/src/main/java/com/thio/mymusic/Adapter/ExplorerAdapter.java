package com.thio.mymusic.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.thio.mymusic.R;
import com.thio.mymusic.Activity.BoxPlaylist;


import java.util.List;

public class ExplorerAdapter extends RecyclerView.Adapter<ExplorerAdapter.ViewHolder> {
    private List<BoxPlaylist> boxPlaylists;
    private Context context;

    public ExplorerAdapter(List<BoxPlaylist> boxPlaylists, Context context) {
        this.boxPlaylists = boxPlaylists;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_box_playlist, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        BoxPlaylist boxPlaylist = boxPlaylists.get(position);

        // Set data to views in box_playlist layout
        holder.tvItemName.setText(boxPlaylist.getTitle());
        holder.tvItemDescription.setText(boxPlaylist.getSubtitle());

        // Set click listener for item in RecyclerView
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle item click here
                // For example, open BoxPlaylistActivity
                Intent intent = new Intent(context, BoxPlaylist.class);
                // Add additional data if needed (e.g., playlist ID)
                // intent.putExtra("playlist_id", boxPlaylist.getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return boxPlaylists.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgItemPhoto;
        public TextView tvItemName;
        public TextView tvItemDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgItemPhoto = itemView.findViewById(R.id.img_item_photo);
            tvItemName = itemView.findViewById(R.id.tv_item_name);
            tvItemDescription = itemView.findViewById(R.id.tv_item_description);
        }
    }

}