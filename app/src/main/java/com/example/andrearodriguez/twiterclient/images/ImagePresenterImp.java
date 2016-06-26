package com.example.andrearodriguez.twiterclient.images;

import com.example.andrearodriguez.twiterclient.images.events.ImageEvent;
import com.example.andrearodriguez.twiterclient.images.ui.ImagesView;
import com.example.andrearodriguez.twiterclient.lib.base.EventBus;

import org.greenrobot.eventbus.Subscribe;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public class ImagePresenterImp implements ImagePresenter{

    private ImagesView view;
    private EventBus eventBus;
    private ImagesInteractor interactor;

    public ImagePresenterImp(ImagesView view, EventBus eventBus, ImagesInteractor interactor) {
        this.view = view;
        this.eventBus = eventBus;
        this.interactor = interactor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);

    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        view = null;
    }

    @Override
    public void getImagesTweets() {
        if(view != null){
            view.hideImages();
            view.showProgress();
        }
        interactor.execute();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImageEvent event) {
        String msgError = event.getError();
        if(view != null){
            view.showImages();
            view.hideProgress();
            if(msgError != null){
                view.onError(msgError);
            }else{
                view.setContent(event.getImages());
            }
        }
    }
}
