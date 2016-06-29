package com.example.andrearodriguez.twiterclient.hashtag.ui;

import com.example.andrearodriguez.twiterclient.entities.Hashtag;

import java.util.List;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public interface HashtagView {
    void showHashtags();
    void hideHashtags();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Hashtag> items);
}
