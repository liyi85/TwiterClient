package com.example.andrearodriguez.twiterclient;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.example.andrearodriguez.twiterclient.hashtag.di.DaggerHashtagComponent;
import com.example.andrearodriguez.twiterclient.hashtag.di.HashtagComponent;
import com.example.andrearodriguez.twiterclient.hashtag.di.HashtagModule;
import com.example.andrearodriguez.twiterclient.hashtag.ui.HashtagView;
import com.example.andrearodriguez.twiterclient.images.di.DaggerImagesComponent;
import com.example.andrearodriguez.twiterclient.images.di.ImagesComponent;
import com.example.andrearodriguez.twiterclient.images.di.ImagesModule;
import com.example.andrearodriguez.twiterclient.images.ui.ImagesView;
import com.example.andrearodriguez.twiterclient.lib.base.di.LibsModule;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by andrearodriguez on 6/22/16.
 */
public class TwitterClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }
    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, com.example.andrearodriguez.twiterclient.images.ui.adapters.OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModule(new LibsModule(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();

    }
    public HashtagComponent getHashtagComponent(HashtagView view, com.example.andrearodriguez.twiterclient.hashtag.ui.adapters.OnItemClickListener clickListener){
        return DaggerHashtagComponent
                .builder()
                .libsModule(new LibsModule(null))
                .hashtagModule(new HashtagModule(view, clickListener))
                .build();

    }

}
