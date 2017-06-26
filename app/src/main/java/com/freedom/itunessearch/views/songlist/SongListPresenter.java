package com.freedom.itunessearch.views.songlist;

import com.freedom.itunessearch.api.APIService;
import com.freedom.itunessearch.api.ServiceFactory;
import com.freedom.itunessearch.api.model.TrackModel;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mayur on 6/25/2017.
 */

class SongListPresenter implements SongListContract.Presenter {

    private SongListContract.View songListView;

    SongListPresenter(SongListContract.View songListView) {
        this.songListView = songListView;
    }

    @Override
    public void getTracks(String term) {
        APIService service = ServiceFactory.getInstance();
        Call<TrackModel> trackModelCall = service.getTracks(term);
        trackModelCall.enqueue(new Callback<TrackModel>() {
            @Override
            public void onResponse(Call<TrackModel> call, Response<TrackModel> response) {
                TrackModel trackModel = response.body();
                if (trackModel.getResultCount() > 0) {
                    songListView.displayTracks(trackModel.getTracks());
                } else {
                    songListView.displayMessage("No songs found, Try again.");
                }
            }

            @Override
            public void onFailure(Call<TrackModel> call, Throwable t) {
                songListView.displayMessage("No songs found, Try again.");
            }
        });
    }
}