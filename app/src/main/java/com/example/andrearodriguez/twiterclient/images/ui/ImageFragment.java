package com.example.andrearodriguez.twiterclient.images.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.andrearodriguez.twiterclient.R;
import com.example.andrearodriguez.twiterclient.TwitterClientApp;
import com.example.andrearodriguez.twiterclient.entities.Image;
import com.example.andrearodriguez.twiterclient.images.ImagePresenter;
import com.example.andrearodriguez.twiterclient.images.di.ImagesComponent;
import com.example.andrearodriguez.twiterclient.images.ui.adapters.ImagesAdapter;
import com.example.andrearodriguez.twiterclient.images.ui.adapters.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;


import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class ImageFragment extends Fragment implements ImagesView, OnItemClickListener {

    @Bind(R.id.container)
    FrameLayout container;
    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;

    @Inject
    ImagesAdapter adapter;
    @Inject
    ImagePresenter presenter;


    public ImageFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);

        ButterKnife.bind(this, view);
        setupInjection();
        setupRecyclerView();
        presenter.getImagesTweets();
        return view;
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp)getActivity().getApplication();
        ImagesComponent imagesComponents = app.getImagesComponent(this, this, this);
        imagesComponents.inject(this);
    }

    @Override
    public void onResume() {
        super.onResume();
        presenter.onResume();
    }

    @Override
    public void onPause() {
        presenter.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        presenter.onDestroy();
        super.onDestroy();
    }

    @Override
    public void showImages() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideImages() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void showProgress() {
        progressbar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        progressbar.setVisibility(View.GONE);
    }

    @Override
    public void onError(String error) {
        Snackbar.make(container, error, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void setContent(List<Image> items) {
        adapter.setItems(items);
    }

    @Override
    public void onItemClick(Image image) {
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(image.getTweetUrl()));
        startActivity(intent);
    }
}
