package com.freedom.itunessearch.views.songlist;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.freedom.itunessearch.R;
import com.freedom.itunessearch.api.model.Track;
import com.freedom.itunessearch.views.songdetail.SongDetailView;

import java.util.List;

/**
 * Created by Mayur on 6/25/2017.
 */

public class SongAdapter extends RecyclerView.Adapter<SongAdapter.MyViewHolder> {

    Context context;
    private List<Track> trackList;

    class MyViewHolder extends RecyclerView.ViewHolder {
        LinearLayout row;
        ImageView imgTrackArtwork;
        TextView txtTrackName, txtArtistNameNGenre, txtPrice;

        MyViewHolder(View view) {
            super(view);
            row = (LinearLayout) view.findViewById(R.id.song_item_row);
            imgTrackArtwork = (ImageView) view.findViewById(R.id.artwork);
            txtTrackName = (TextView) view.findViewById(R.id.track_name);
            txtArtistNameNGenre = (TextView) view.findViewById(R.id.artist_name_and_genre);
            txtPrice = (TextView) view.findViewById(R.id.price);
            row.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent detail = new Intent(context, SongDetailView.class);
                    detail.putExtra("track", trackList.get(getAdapterPosition()));
                    context.startActivity(detail);
                }
            });
        }
    }

    SongAdapter(Context context, List<Track> trackList) {
        this.context = context;
        this.trackList = trackList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.song_item, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Track track = trackList.get(position);

        final String artworkUrl = track.getArtworkUrl100();
        Glide.with(context).load(artworkUrl).placeholder(R.drawable.ic_logo).into(holder.imgTrackArtwork);

        holder.txtTrackName.setText(track.getTrackName());
        holder.txtArtistNameNGenre.setText(track.getArtistName() + " | " + track.getPrimaryGenreName());
        holder.txtPrice.setText(String.format("US $ %s", String.valueOf(track.getTrackPrice())));
    }

    @Override
    public int getItemCount() {
        return trackList.size();
    }
}