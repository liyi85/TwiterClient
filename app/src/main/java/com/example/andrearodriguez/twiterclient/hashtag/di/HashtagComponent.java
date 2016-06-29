package com.example.andrearodriguez.twiterclient.hashtag.di;

import com.example.andrearodriguez.twiterclient.hashtag.HashtagPresenter;
import com.example.andrearodriguez.twiterclient.hashtag.ui.HashtagFragment;
import com.example.andrearodriguez.twiterclient.lib.base.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 6/25/16.
 */
@Singleton @Component(modules = {LibsModule.class, HashtagModule.class})
public interface HashtagComponent {
    void inject(HashtagFragment fragment);
    HashtagPresenter getPresenter();
}
