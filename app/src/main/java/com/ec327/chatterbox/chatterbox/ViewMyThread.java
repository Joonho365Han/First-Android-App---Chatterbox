package com.ec327.chatterbox.chatterbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.TextView;

public class ViewMyThread extends Activity {

    /* This is the Constructor in context of Java for the Android app. */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.viewthread);
        getActionBar().setDisplayHomeAsUpEnabled(true);

        TextView title = (TextView) findViewById(R.id.viewThread_Title);
        TextView season_episode = (TextView) findViewById(R.id.viewThread_Season);
        TextView writer = (TextView) findViewById(R.id.viewThread_Writer);
        TextView content = (TextView) findViewById(R.id.viewThread_Content);
        TextView comments  = (TextView) findViewById(R.id.viewThread_Comments);

        String[] post = getIntent().getStringArrayExtra("Contents");

        //The thread content is displayed withthe info retrieved from the intent from the my activity screen.
        title.setText(post[1]);
        season_episode.setText("<Season " + post[2] + " Episode " + post[3] + ">");
        writer.setText("By " + post[4]);
        content.setText(post[5]);
        comments.setText("<CANNOT VIEW OTHERS' COMMENTS WITH MY ACTIVITY>");
        if(post[6].length()!=0) {
            comments.setText("USER COMMENT:\n" + post[6]);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_view_mythread, menu);
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
        } else if (id == R.id.action_myShows){
            toMyShows();
            return true;
        } else if(id == android.R.id.home){
            toMyActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toAddShows() {
        Intent intent = new Intent(this, AddShows.class);
        startActivity(intent);
    }

    private void toMyShows() {
        Intent intent = new Intent(this,MyShows.class);
        startActivity(intent);
    }

    private void toMyActivity() {
        Intent intent = new Intent(this,MyActivity.class);
        startActivity(intent);
    }
}
