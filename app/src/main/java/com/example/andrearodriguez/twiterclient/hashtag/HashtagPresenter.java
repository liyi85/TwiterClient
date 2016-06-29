package com.example.andrearodriguez.twiterclient.hashtag;

import com.example.andrearodriguez.twiterclient.hashtag.events.HashtagEvent;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public interface HashtagPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTwwet();
    void onEventMainThread(HashtagEvent event);
}
