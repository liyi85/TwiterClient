package com.example.andrearodriguez.twiterclient.images.di;

import com.example.andrearodriguez.twiterclient.api.CustomTwitterApiClient;
import com.example.andrearodriguez.twiterclient.entities.Image;
import com.example.andrearodriguez.twiterclient.images.ImagePresenter;
import com.example.andrearodriguez.twiterclient.images.ImagePresenterImp;
import com.example.andrearodriguez.twiterclient.images.ImagesInteractor;
import com.example.andrearodriguez.twiterclient.images.ImagesInteractorImp;
import com.example.andrearodriguez.twiterclient.images.ImagesRespository;
import com.example.andrearodriguez.twiterclient.images.ImagesRespositoryImpl;
import com.example.andrearodriguez.twiterclient.images.ui.ImagesView;
import com.example.andrearodriguez.twiterclient.images.ui.adapters.ImagesAdapter;
import com.example.andrearodriguez.twiterclient.images.ui.adapters.OnItemClickListener;
import com.example.andrearodriguez.twiterclient.lib.base.EventBus;
import com.example.andrearodriguez.twiterclient.lib.base.ImageLoader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by andrearodriguez on 6/25/16.
 */
@Module
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }
    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Image> providesItemsList(){
        return new ArrayList<Image>();
    }
    @Provides
    @Singleton
    ImagePresenter providesImagePresenter(ImagesView view, EventBus eventBus, ImagesInteractor interactor){
        return new ImagePresenterImp(view, eventBus, interactor);
    }
    @Provides
    @Singleton
    ImagesView providesImageView(){
        return this.view;
    }
    @Provides
    @Singleton
    ImagesInteractor providesImageInteractor(ImagesRespository repository){
        return new ImagesInteractorImp(repository);
    }
    @Provides
    @Singleton
    ImagesRespository providesImageRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new ImagesRespositoryImpl(eventBus, client);
    }
    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient (Session session){
        return new CustomTwitterApiClient(session);
    }
    @Provides
    @Singleton
    Session providesTwitter (){
        return Twitter.getSessionManager().getActiveSession();
    }
}
