package com.freedom.itunessearch.views.songdetail;

import com.freedom.itunessearch.api.model.Track;

/**
 * Created by Mayur on 6/26/2017.
 */

public class SongDetailContract {

    interface View {
        void displayMessage(String message);

        void displayTrack(Track track);
    }
}
