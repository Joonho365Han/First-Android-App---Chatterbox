package com.ec327.chatterbox.chatterbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

public class ViewThread extends Activity {

    /* This is the Constructor in context of Java for the Android app. */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewthread);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        //Parse is initiallized not just to exchange data but also to reconnect in case of crash.
        Parse.initialize(this, "sIIPDbEWnnRETu0XlKQL6QMER34bBR3ZPNV2Ibmu", "OGFvOpzYbYNsb4n9xEHIaT8vdiZFvXZOXxFAzer4");

        refreshThread();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_viewthread, menu);
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
        } else if (id == R.id.action_myShows){
            toMyShows();
            return true;
        } else if (id == R.id.action_addComment){
            toAddComment();
            return true;
        } else if(id == android.R.id.home){
            toMainscreen();
            return true;
        } else if(id == R.id.action_refresh){
            refreshThread();
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

    private void toMyShows() {
        Intent intent = new Intent(this,MyShows.class);
        startActivity(intent);
    }

    private void toAddComment() {
        Intent intent = new Intent(this,AddComment.class);
        intent.putExtra("Contents", getIntent().getStringArrayExtra("Contents"));
        intent.addFlags(getIntent().getFlags());
        startActivity(intent);
    }

    private void toMainscreen() {
        Intent intent = new Intent(this,Mainscreen.class);
        intent.addFlags(getIntent().getFlags());
        startActivity(intent);
    }

    private void refreshThread(){

        final TextView title = (TextView) findViewById(R.id.viewThread_Title);
        final TextView season_episode = (TextView) findViewById(R.id.viewThread_Season);
        final TextView writer = (TextView) findViewById(R.id.viewThread_Writer);
        final TextView content = (TextView) findViewById(R.id.viewThread_Content);
        final TextView comments  = (TextView) findViewById(R.id.viewThread_Comments);

        String[] post = getIntent().getStringArrayExtra("Contents");

        //Uses the thread ID and category name to retrieve the thread specific info and displays them on its according viewtext layout.
        ParseQuery<ParseObject> query = ParseQuery.getQuery(post[0]);
        query.getInBackground(post[1], new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject object, ParseException e) {
                if (e == null) {
                    // object will be your thread
                    title.setText(object.getString("title"));
                    season_episode.setText("<Season " + object.getString("season") + " Episode " + object.getString("episode") + ">");
                    writer.setText("By " + object.getString("writer"));
                    content.setText(object.getString("content"));
                    comments.setText(object.getString("comments"));
                }
            }
        });
    }
}