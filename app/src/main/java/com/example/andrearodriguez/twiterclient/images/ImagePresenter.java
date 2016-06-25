package com.example.andrearodriguez.twiterclient.images;

import com.example.andrearodriguez.twiterclient.images.events.ImageEvent;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public interface ImagePresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImagesTweets();
    void onEventMainThread(ImageEvent event);
}
