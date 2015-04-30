package com.ec327.chatterbox.chatterbox;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class MyActivity extends ListActivity{

    ArrayList<UserThread> threads;

    /* This is the Constructor in context of Java for the Android app. */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myactivity);

        //Parse is initiallized not just to exchange data but also to reconnect in case of crash.
        Parse.initialize(this, "sIIPDbEWnnRETu0XlKQL6QMER34bBR3ZPNV2Ibmu", "OGFvOpzYbYNsb4n9xEHIaT8vdiZFvXZOXxFAzer4");

        threads = new ArrayList<>();
        ArrayAdapter<UserThread> adapter = new ArrayAdapter<>(this, R.layout.thread_template, threads);
        setListAdapter(adapter);

        refreshThreadList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_myactivity, menu);
        return true;
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
        } else if (id == R.id.action_myShows) {
            toMyShows();
            return true;
        } else if(id == R.id.action_refresh){
            refreshThreadList();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toAddShows() {
        Intent intent = new Intent(this,AddShows.class);
        startActivity(intent);
    }

    private void toMyShows() {
        Intent intent = new Intent(this,MyShows.class);
        startActivity(intent);
    }

    private void refreshThreadList() {

        ParseQuery<ParseObject> query = ParseQuery.getQuery("My_Activity_"+ParseUser.getCurrentUser().getUsername());
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> threadList, ParseException e) {
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    threads.clear();
                    for (ParseObject threadObject : threadList) {
                        UserThread thread = new UserThread(threadObject.getObjectId(), threadObject.getString("title"), threadObject.getString("season"), threadObject.getString("episode"), threadObject.getString("writer"), threadObject.getString("content"), threadObject.getString("comments"), threadObject.getCreatedAt().toString());
                        threads.add(thread);
                    }
                    ((ArrayAdapter<UserThread>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        //In MyActivity, threads are created without the show info the thread was posted under. Thus, the strings
        //that consist of threads are directly passe onto the thread display screen.
        Intent intent = new Intent(this, ViewMyThread.class);
        UserThread clicked = threads.get(position);
        String[] contents = {clicked.getId(), clicked.getTitle(), clicked.getSeason(), clicked.getEpisode(), clicked.getWriter(), clicked.getContent(), clicked.getComments(), clicked.getCreatedAt()};
        intent.putExtra("Contents", contents);
        intent.addFlags(getIntent().getFlags());
        startActivity(intent);
    }
}
