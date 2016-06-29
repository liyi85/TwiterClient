package com.example.andrearodriguez.twiterclient.hashtag.di;

import com.example.andrearodriguez.twiterclient.api.CustomTwitterApiClient;
import com.example.andrearodriguez.twiterclient.entities.Hashtag;
import com.example.andrearodriguez.twiterclient.hashtag.HashtagInteractor;
import com.example.andrearodriguez.twiterclient.hashtag.HashtagInteractorImp;
import com.example.andrearodriguez.twiterclient.hashtag.HashtagPresenter;
import com.example.andrearodriguez.twiterclient.hashtag.HashtagPresenterImp;
import com.example.andrearodriguez.twiterclient.hashtag.HashtagRespository;
import com.example.andrearodriguez.twiterclient.hashtag.HashtagRespositoryImpl;
import com.example.andrearodriguez.twiterclient.hashtag.ui.HashtagView;
import com.example.andrearodriguez.twiterclient.hashtag.ui.adapters.HashtagAdapter;
import com.example.andrearodriguez.twiterclient.hashtag.ui.adapters.OnItemClickListener;
import com.example.andrearodriguez.twiterclient.lib.base.EventBus;
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
public class HashtagModule {
    private HashtagView view;
    private OnItemClickListener clickListener;


    public HashtagModule(HashtagView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagAdapter providesAdapter(List<Hashtag> dataset, OnItemClickListener clickListener){
        return new HashtagAdapter(dataset, clickListener);
    }
    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }
    @Provides
    @Singleton
    List<Hashtag> providesItemsList(){
        return new ArrayList<Hashtag>();
    }
    @Provides
    @Singleton
    HashtagPresenter providesHashtagPresenter(HashtagView view, EventBus eventBus, HashtagInteractor interactor){
        return new HashtagPresenterImp(view, eventBus, interactor);
    }
    @Provides
    @Singleton
    HashtagView providesHashtagView(){
        return this.view;
    }
    @Provides
    @Singleton
    HashtagInteractor providesHashtagInteractor(HashtagRespository repository){
        return new HashtagInteractorImp(repository);
    }
    @Provides
    @Singleton
    HashtagRespository providesHashtagRepository(EventBus eventBus, CustomTwitterApiClient client){
        return new HashtagRespositoryImpl(eventBus, client);
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
