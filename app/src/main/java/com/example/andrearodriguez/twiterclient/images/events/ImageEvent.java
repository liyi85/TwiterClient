package com.example.andrearodriguez.twiterclient.images.events;

import com.example.andrearodriguez.twiterclient.entities.Image;

import java.util.List;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public class ImageEvent {
    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
