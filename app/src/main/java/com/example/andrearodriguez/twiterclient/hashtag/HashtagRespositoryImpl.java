package com.example.andrearodriguez.twiterclient.hashtag;

import android.util.Log;

import com.example.andrearodriguez.twiterclient.api.CustomTwitterApiClient;
import com.example.andrearodriguez.twiterclient.entities.Hashtag;
import com.example.andrearodriguez.twiterclient.hashtag.events.HashtagEvent;
import com.example.andrearodriguez.twiterclient.lib.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public class HashtagRespositoryImpl implements HashtagRespository{

    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public HashtagRespositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    private boolean containsHashtag(Tweet tweet){
        return tweet.entities !=null &&
                tweet.entities.hashtags != null &&
                !tweet.entities.hashtags.isEmpty();
    }

    private void post(List<Hashtag> hashtags){
        post(hashtags, null);
    }

    private void post(String error){
        post(null, error);
    }

    private void post(List<Hashtag> items, String error){
        HashtagEvent event = new HashtagEvent();
        event.setError(error);
        event.setHashtags(items);
        eventBus.post(event);
    }

    @Override
    public void getHashtag() {
        Log.i("getHashtag","repository");
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                Log.i("exito", "");
                List<Hashtag> items = new ArrayList<Hashtag>();
                for(Tweet tweet : result.data){
                    if(containsHashtag(tweet)){
                        Hashtag tweetModel = new Hashtag();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);
                        tweetModel.setTweetText(tweet.text);

                        List<String> hashtags = new ArrayList<String>();
                        for (HashtagEntity hashtag : tweet.entities.hashtags){
                            hashtags.add(hashtag.text);
                        }
                        tweetModel.setHashtag(hashtags);

                        items.add(tweetModel);
                    }
                }
                Collections.sort(items, new Comparator<Hashtag>() {
                    @Override
                    public int compare(Hashtag hashtag, Hashtag rhs) {
                        return rhs.getFavoriteCount() - hashtag.getFavoriteCount();
                    }
                });
                post(items);
            }

            @Override
            public void failure(TwitterException exception) {
                Log.i("pailitos", "");
                post(exception.getLocalizedMessage());
            }
        };
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true, callback);
    }
}
