package com.example.andrearodriguez.twiterclient.images;

/**
 * Created by andrearodriguez on 6/25/16.
 */
public class ImagesInteractorImp implements ImagesInteractor{

    private ImagesRespository repository;

    public ImagesInteractorImp(ImagesRespository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
