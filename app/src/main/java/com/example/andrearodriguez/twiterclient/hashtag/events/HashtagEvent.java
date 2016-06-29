package com.example.andrearodriguez.twiterclient.hashtag.events;

import com.example.andrearodriguez.twiterclient.entities.Hashtag;

import java.util.List;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public class HashtagEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
