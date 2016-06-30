package com.example.andrearodriguez.twiterclient.hashtag.ui;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ProgressBar;

import com.example.andrearodriguez.twiterclient.R;
import com.example.andrearodriguez.twiterclient.TwitterClientApp;
import com.example.andrearodriguez.twiterclient.entities.Hashtag;
import com.example.andrearodriguez.twiterclient.hashtag.HashtagPresenter;
import com.example.andrearodriguez.twiterclient.hashtag.di.HashtagComponent;
import com.example.andrearodriguez.twiterclient.hashtag.ui.adapters.HashtagAdapter;
import com.example.andrearodriguez.twiterclient.hashtag.ui.adapters.OnItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HashtagFragment extends Fragment implements HashtagView, OnItemClickListener {


    @Bind(R.id.progressbar)
    ProgressBar progressbar;
    @Bind(R.id.recyclerView)
    RecyclerView recyclerView;
    @Bind(R.id.container)
    FrameLayout container;

    //to inject para no inicializarlo en el cosntructor, sin esta injeccion se presentara un error en tiempo de  ejecucion
    @Inject
    HashtagAdapter adapter;
    @Inject
    HashtagPresenter presenter;



    public HashtagFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_content, container, false);
        ButterKnife.bind(this, view);

        setupInjection();

        setupRecyclerView();

        presenter.getHashtagTwwet();

        return view;
    }

    //    ===================================PRESENTER====================================================
    @Override
    public void onResume() {
        presenter.onResume();
        super.onResume();
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

//    ============================================================================================

    private void setupRecyclerView() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);
    }

    private void setupInjection() {
        TwitterClientApp app = (TwitterClientApp) getActivity().getApplication();
        //el componente viene el applicationCas
        HashtagComponent hashtagComponent = app.getHashtagComponent(this, this);
        hashtagComponent.inject(this);

    }

//    ============================View============================================================

    @Override
    public void showHashtags() {
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideHashtags() {
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
    public void setContent(List<Hashtag> items) {
        adapter.setItems(items);
    }

    //    ===============================onClick===========================================
    @Override
    public void onItemClick(Hashtag hashtag) {
        Log.i("url", hashtag.getTwitterURL());
        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(hashtag.getTwitterURL()));
        startActivity(intent);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}