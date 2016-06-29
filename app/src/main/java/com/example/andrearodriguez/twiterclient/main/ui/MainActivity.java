package com.example.andrearodriguez.twiterclient.main.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.andrearodriguez.twiterclient.LoginActivity;
import com.example.andrearodriguez.twiterclient.R;
import com.example.andrearodriguez.twiterclient.hashtag.ui.HashtagFragment;
import com.example.andrearodriguez.twiterclient.images.ui.ImageFragment;
import com.example.andrearodriguez.twiterclient.main.adapter.MainSectionsPagerAdapter;
import com.twitter.sdk.android.Twitter;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @Bind(R.id.toolbar)
    Toolbar toolbar;
    @Bind(R.id.appbar)
    AppBarLayout appbar;
    @Bind(R.id.container)
    ViewPager viewPager;
    @Bind(R.id.tabs)
    TabLayout tabs;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        setSupportActionBar(toolbar);
        setupAdapter();
    }

    private void setupAdapter() {
        Fragment[] fragments = new Fragment[]{new ImageFragment(), new HashtagFragment()};
        String[] titles = new String[]{getString(R.string.main_header_images), getString(R.string.main_header_hastags)};
        MainSectionsPagerAdapter adapter = new MainSectionsPagerAdapter(getSupportFragmentManager(), titles, fragments);

        viewPager.setAdapter(adapter);
        tabs.setupWithViewPager(viewPager);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        Log.i("ABC", "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_logout) {
            logout();
        }
        return super.onOptionsItemSelected(item);
    }

    private void logout() {
        Twitter.logOut();
        Intent intent = new Intent(this, LoginActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP
                | Intent.FLAG_ACTIVITY_NEW_TASK
                | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
