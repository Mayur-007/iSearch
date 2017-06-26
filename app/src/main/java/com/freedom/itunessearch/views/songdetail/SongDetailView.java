package com.freedom.itunessearch.views.songdetail;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.freedom.itunessearch.R;
import com.freedom.itunessearch.api.model.Track;

/**
 * Created by Mayur on 6/26/2017.
 */

public class SongDetailView extends AppCompatActivity implements SongDetailContract.View {

    Context context;
    LinearLayout main;
    ImageView imgArtwork;
    TextView txtArtistName, txtGenre, txtPrice;
    VideoView videoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.song_detail);

        context = SongDetailView.this;

        main = (LinearLayout) findViewById(R.id.song_detail_main);
        imgArtwork = (ImageView) findViewById(R.id.imgArtworkDetail);
        txtArtistName = (TextView) findViewById(R.id.artist_name_detail);
        txtGenre = (TextView) findViewById(R.id.genre_detail);
        txtPrice = (TextView) findViewById(R.id.price_detail);
        videoView = (VideoView) findViewById(R.id.videoView);

        try {
            displayTrack((Track) getIntent().getSerializableExtra("track"));
        } catch (Exception e) {
            displayMessage("Problem while getting song info, Try again.");
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void displayMessage(String message) {
        Snackbar.make(main, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void displayTrack(Track track) {

        if(getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(track.getTrackName());
        }

        String artworkUrl = track.getArtworkUrl100();
        Glide.with(context).load(artworkUrl).placeholder(R.drawable.ic_logo).into(imgArtwork);

        txtArtistName.setText(track.getArtistName());
        txtGenre.setText(track.getPrimaryGenreName());
        txtPrice.setText(String.format("US $ %s", String.valueOf(track.getTrackPrice())));

        MediaController mediaController = new MediaController(this);
        mediaController.setAnchorView(videoView);
        Uri video = Uri.parse(track.getPreviewUrl());
        videoView.setMediaController(mediaController);
        videoView.setVideoURI(video);
        videoView.start();
    }
}