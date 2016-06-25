package com.example.andrearodriguez.twiterclient.images.ui;

import com.example.andrearodriguez.twiterclient.entities.Image;

import java.util.List;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public interface ImagesView {
    void showElements();
    void hideElements();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}
