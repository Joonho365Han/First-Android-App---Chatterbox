package com.ec327.chatterbox.chatterbox;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TabHost;

import com.parse.Parse;

public class Mainscreen extends Activity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;

    MainTabEpisode episodeTab;
    MainTabSeason seasonTab;
    MainTabSeries seriesTab;

    /* This is the Constructor in context of Java for the Android app. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mainscreen);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Parse is initiallized not just to exchange data but also to reconnect in case of crash.
        Parse.initialize(this, "sIIPDbEWnnRETu0XlKQL6QMER34bBR3ZPNV2Ibmu", "OGFvOpzYbYNsb4n9xEHIaT8vdiZFvXZOXxFAzer4");

        ImageView showIcon = (ImageView) findViewById(R.id.main_showIcon);

        //gets the info from the myshows screen which show icon the user clicked and displays the same icon on the top.
        if(getIntent().getFlags() == 1) {
            showIcon.setBackgroundResource(R.drawable.arrow);
        }else if(getIntent().getFlags() == 2){
            showIcon.setBackgroundResource(R.drawable.daredevil);
        }else if(getIntent().getFlags() == 3){
            showIcon.setBackgroundResource(R.drawable.flash);
        }else if(getIntent().getFlags() == 4){
            showIcon.setBackgroundResource(R.drawable.fob);
        }else if(getIntent().getFlags() == 5){
            showIcon.setBackgroundResource(R.drawable.got);
        }else if(getIntent().getFlags() == 6){
            showIcon.setBackgroundResource(R.drawable.greys_anatomy);
        }else if(getIntent().getFlags() == 7){
            showIcon.setBackgroundResource(R.drawable.hoc);
        }else if(getIntent().getFlags() == 8){
            showIcon.setBackgroundResource(R.drawable.madmen);
        }else if(getIntent().getFlags() == 9){
            showIcon.setBackgroundResource(R.drawable.htgawm);
        }else if(getIntent().getFlags() == 10){
            showIcon.setBackgroundResource(R.drawable.once);
        }else if(getIntent().getFlags() == 11){
            showIcon.setBackgroundResource(R.drawable.silicon_valley);
        }else{
            showIcon.setBackgroundResource(R.drawable.the100);
        }

        TabHost forums = (TabHost) findViewById(R.id.mainTabs);
        forums.setup();

        //Creates the format each tab button will show and displays the Episode tab.
        TabHost.TabSpec spec1 = forums.newTabSpec("tab1");
        spec1.setContent(R.id.main_episode);
        spec1.setIndicator("Episode");
        forums.addTab(spec1);

        //Creates the format each tab button will show and displays the Seasons tab.
        TabHost.TabSpec spec2 = forums.newTabSpec("tab2");
        spec2.setContent(R.id.main_season);
        spec2.setIndicator("Season");
        forums.addTab(spec2);

        //Creates the format each tab button will show and displays the Series tab.
        TabHost.TabSpec spec3 = forums.newTabSpec("tab3");
        spec3.setContent(R.id.main_series);
        spec3.setIndicator("Series");
        forums.addTab(spec3);

        //Each tabs' thread list is a fragment. Each fragments are initialized here.
        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();
        episodeTab = new MainTabEpisode();
        fragmentTransaction.add(R.id.main_episode, episodeTab);
        seasonTab = new MainTabSeason();
        fragmentTransaction.add(R.id.main_season, seasonTab);
        seriesTab = new MainTabSeries();
        fragmentTransaction.add(R.id.main_series, seriesTab);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_mainscreen, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_addShows) {
            toAddShows();
            return true;
        } else if (id == R.id.action_myActivity) {
            toMyActivity();
            return true;
        } else if (id == R.id.action_createThread){
            toCreateThread();
            return true;
        } else if(id == R.id.action_refresh){
            episodeTab.refreshThreadList();
            seasonTab.refreshThreadList();
            seriesTab.refreshThreadList();
            return true;
        } else if(id == android.R.id.home){
            toMyShows();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toAddShows() {
        Intent intent = new Intent(this, AddShows.class);
        startActivity(intent);
    }

    private void toMyActivity() {
        Intent intent = new Intent(this,MyActivity.class);
        startActivity(intent);
    }

    private void toCreateThread() {
        Intent intent = new Intent(this,CreateThread.class);
        intent.addFlags(getIntent().getFlags());
        startActivity(intent);
    }

    private void toMyShows() {
        Intent intent = new Intent(this,MyShows.class);
        startActivity(intent);
    }
}