package com.example.andrearodriguez.twiterclient.entities;

import java.util.List;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public class Hashtag {
    private String id;
    private String tweetText;
    private int favoriteCount;
    private List<String> hashtag;
    private final static String BASE_TWEET_URL = "https://twitter.com/null/status";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTweetText() {
        return tweetText;
    }

    public void setTweetText(String tweetText) {
        this.tweetText = tweetText;
    }

    public int getFavoriteCount() {
        return favoriteCount;
    }

    public void setFavoriteCount(int favoriteCount) {
        this.favoriteCount = favoriteCount;
    }

    public List<String> getHashtag() {
        return hashtag;
    }

    public void setHashtag(List<String> hashtag) {
        this.hashtag = hashtag;
    }

    public String getTwitterURL (){
        return BASE_TWEET_URL + this.id;
    }
}
