package com.example.andrearodriguez.twiterclient.images.di;

import com.example.andrearodriguez.twiterclient.images.ImagePresenter;
import com.example.andrearodriguez.twiterclient.images.ui.ImageFragment;
import com.example.andrearodriguez.twiterclient.lib.base.di.LibsModule;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by andrearodriguez on 6/25/16.
 */
@Singleton @Component(modules = {LibsModule.class, ImagesModule.class})
public interface ImagesComponent {
    void inject(ImageFragment fragment);
    ImagePresenter getPresenter();
}
