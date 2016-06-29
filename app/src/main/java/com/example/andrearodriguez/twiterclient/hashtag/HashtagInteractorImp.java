package com.example.andrearodriguez.twiterclient.hashtag;

import android.util.Log;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public class HashtagInteractorImp implements HashtagInteractor{

    HashtagRespository repository;

    public HashtagInteractorImp(HashtagRespository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        Log.i("getHashtag","interactor");
        repository.getHashtag();
    }
}
