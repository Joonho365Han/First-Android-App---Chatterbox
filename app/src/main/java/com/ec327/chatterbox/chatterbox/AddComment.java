package com.ec327.chatterbox.chatterbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.parse.GetCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class AddComment extends Activity {

    private EditText comment;

    /* This is the Constructor in context of Java for the Android app. */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_comment);

        //Parse is initiallized not just to exchange data but also to reconnect in case of crash.
        Parse.initialize(this, "sIIPDbEWnnRETu0XlKQL6QMER34bBR3ZPNV2Ibmu", "OGFvOpzYbYNsb4n9xEHIaT8vdiZFvXZOXxFAzer4");

        comment = (EditText) findViewById(R.id.comment_content_input);
    }

    public void postComment(View view) {

        final String commentContent = "\n\n" + comment.getText().toString().trim() + "\n- by " + ParseUser.getCurrentUser().getUsername();

        //If the user does not enter anything and tries to post a comment, error message shows up.
        if (comment.getText().toString().isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(AddComment.this);
            builder.setMessage(R.string.enter_comment)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        //An array of thread original ID and the category name is received from the thread sceen.
        //This will allows users to access the thread on cloud.
        String[] post = getIntent().getStringArrayExtra("Contents");

        ParseQuery<ParseObject> query = ParseQuery.getQuery(post[0]);
        query.getInBackground(post[1], new GetCallback<ParseObject>() {

            @Override
            public void done(ParseObject object, ParseException e) {
                if(e==null) {
                    //Saves the thread on parse cloud with the comment string object appended witho your comment.
                    String instant = object.getString("comments") + commentContent;
                    object.remove("comments");
                    object.put("comments", instant);
                    object.saveEventually();

                    //Creates a copy of the thread the user just commented on and the comment itself.
                    ParseObject newUserThread = new ParseObject("My_Activity_"+ParseUser.getCurrentUser().getUsername());
                    newUserThread.put("title", object.getString("title"));
                    newUserThread.put("season", object.getString("season"));
                    newUserThread.put("episode", object.getString("episode"));
                    newUserThread.put("writer", object.getString("writer"));
                    newUserThread.put("content", object.getString("content"));
                    newUserThread.put("comments", comment.getText().toString().trim());
                    newUserThread.saveEventually();

                }  else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });

        Intent intent = new Intent(this,ViewThread.class);
        intent.putExtra("Contents", getIntent().getStringArrayExtra("Contents"));
        intent.addFlags(getIntent().getFlags());
        startActivity(intent);
    }
}