package com.example.andrearodriguez.twiterclient.images;

import com.example.andrearodriguez.twiterclient.api.CustomTwitterApiClient;
import com.example.andrearodriguez.twiterclient.entities.Image;
import com.example.andrearodriguez.twiterclient.images.events.ImageEvent;
import com.example.andrearodriguez.twiterclient.lib.base.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public class ImagesRespositoryImpl implements ImagesRespository{

    private EventBus eventBus;
    private CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 10;

    public ImagesRespositoryImpl(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    @Override
    public void getImages() {
        Callback<List<Tweet>> callback = new Callback<List<Tweet>>() {
            @Override
            public void success(Result<List<Tweet>> result) {
                List<Image> items = new ArrayList<Image>();
                for(Tweet tweet : result.data){
                    if(containsImages(tweet)){
                        Image tweetModel = new Image();

                        tweetModel.setId(tweet.idStr);
                        tweetModel.setFavoriteCount(tweet.favoriteCount);

                        String tweetText = tweet.text;
                        int index = tweetText.indexOf("http");
                        if(index>0){
                            tweetText = tweetText.substring(0,index);
                        }
                        tweetModel.setTweetText(tweetText);

                        MediaEntity currentPhoto = tweet.entities.media.get(0);
                        String imageUrl = currentPhoto.mediaUrl;
                        tweetModel.setImageURL(imageUrl);

                        items.add(tweetModel);
                    }
                }
                Collections.sort(items, new Comparator<Image>() {
                    @Override
                    public int compare(Image image, Image rhs) {
                        return rhs.getFavoriteCount() - image.getFavoriteCount();
                    }
                });
                post(items);
            }

            @Override
            public void failure(TwitterException exception) {
                post(exception.getLocalizedMessage());
            }
        };
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true, callback);
    }

    private boolean containsImages(Tweet tweet){
        return tweet.entities !=null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private void post(List<Image> items){
        post(items, null);
    }

    private void post(String error){
        post(null, error);
    }

    private void post(List<Image> items, String error){
        ImageEvent event = new ImageEvent();
        event.setError(error);
        event.setImages(items);
        eventBus.post(event);
    }
}
