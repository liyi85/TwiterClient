package com.example.andrearodriguez.twiterclient.hashtag;

import android.util.Log;

import com.example.andrearodriguez.twiterclient.hashtag.events.HashtagEvent;
import com.example.andrearodriguez.twiterclient.hashtag.ui.HashtagView;
import com.example.andrearodriguez.twiterclient.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public class HashtagPresenterImp implements HashtagPresenter{

    private HashtagView view;
    private EventBus eventBus;
    private HashtagInteractor interactor;

    public HashtagPresenterImp(HashtagView view, EventBus eventBus, HashtagInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        Log.i("register","presenter");
        eventBus.register(this);

    }

    @Override
    public void onPause() {
        Log.i("unregister","presenter");
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getHashtagTwwet() {
        Log.i("getHashtag","presenter");
//        if(view != null){
//            view.hideHashtags();
//            view.showProgress();
//        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(HashtagEvent event) {
        String msgError = event.getError();
        if(view != null){
            view.showHashtags();
            view.hideProgress();
            if(msgError != null){
                view.onError(msgError);
            }else{
                view.setContent(event.getHashtags());
            }
        }
    }
}
