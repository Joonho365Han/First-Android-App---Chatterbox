package com.ec327.chatterbox.chatterbox;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.parse.Parse;
import com.parse.ParseObject;
import com.parse.ParseUser;


public class CreateThread extends Activity {

    private EditText titleEditText;
    private EditText seasonEditText;
    private EditText episodeEditText;
    private EditText contentEditText;
    private String typeSelected;
    private String showTitle;

    /* This is the Constructor in context of Java for the Android app. */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.createthread);

        //Parse is initiallized not just to exchange data but also to reconnect in case of crash.
        Parse.initialize(this, "sIIPDbEWnnRETu0XlKQL6QMER34bBR3ZPNV2Ibmu", "OGFvOpzYbYNsb4n9xEHIaT8vdiZFvXZOXxFAzer4");

        titleEditText = (EditText) findViewById(R.id.thread_title_input);
        seasonEditText = (EditText) findViewById(R.id.thread_season_input);
        episodeEditText = (EditText) findViewById(R.id.thread_episode_input);
        contentEditText = (EditText) findViewById(R.id.thread_content_input);

        Spinner threadType = (Spinner) findViewById(R.id.thread_post_category);

        //The three lines of code initializes the spinner object, which prompts the user for the thread category.
        ArrayAdapter<CharSequence> threadTypeAdapter = ArrayAdapter.createFromResource(this,R.array.thread_type,android.R.layout.simple_spinner_item);
        threadTypeAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        threadType.setAdapter(threadTypeAdapter);

        //Receives the thread category info selected from the spinner object.
        threadType.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                typeSelected = parent.getItemAtPosition(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                typeSelected = "_Episode";
            }
        });

    }

    public void postThread(View view) {

        String postTitle = titleEditText.getText().toString().trim();
        String postSeason = seasonEditText.getText().toString().trim();
        String postEpisode = episodeEditText.getText().toString().trim();
        String postWriter = ParseUser.getCurrentUser().getUsername().trim();
        String postContent = contentEditText.getText().toString().trim();
        String postComment = "";

        //This line of loop specifies which type of show the Mainscreen is displaying and at the same time
        //specifies the show the user is creating the thread for.
        if (getIntent().getFlags() == 1)
            showTitle = "Arrow_Thread";
        else if (getIntent().getFlags() == 2)
            showTitle = "Daredevil_Thread";
        else if (getIntent().getFlags() == 3)
            showTitle = "Flash_Thread";
        else if (getIntent().getFlags()==4)
            showTitle = "FOB_Thread";
        else if (getIntent().getFlags()==5)
            showTitle = "Game_of_Thrones_Thread";
        else if (getIntent().getFlags()==6)
            showTitle = "Greys_Anatomy_Thread";
        else if (getIntent().getFlags()==7)
            showTitle = "House_of_Cards_Thread";
        else if (getIntent().getFlags()==8)
            showTitle = "Madmen_Thread";
        else if (getIntent().getFlags()==9)
            showTitle = "How_to_Get_Away_With_Murder_Thread";
        else if (getIntent().getFlags()==10)
            showTitle = "Once_Upon_A_Time_Thread";
        else if (getIntent().getFlags()==11)
            showTitle = "Silicon_Valley_Thread";
        else if (getIntent().getFlags()==12)
            showTitle = "The_100_Thread";

        //Check what category the thread is about.
        if(typeSelected.equals("Post is about an episode"))
            typeSelected = "_Episode";
        else if(typeSelected.equals("Post is about a season"))
            typeSelected = "_Season";
        else if(typeSelected.equals("Post is about the series"))
            typeSelected = "_Series";

        //If an edit text box is empty, the thread does not upload.
        if (postTitle.isEmpty() || postContent.isEmpty() || postEpisode.isEmpty() || postSeason.isEmpty()) {
            AlertDialog.Builder builder = new AlertDialog.Builder(CreateThread.this);
            builder.setMessage(R.string.login_error_message)
                    .setPositiveButton(android.R.string.ok, null);
            AlertDialog dialog = builder.create();
            dialog.show();
            return;
        }

        //A new thread is saved as a parse object in the cloud under the category name as the show and thread type.
        ParseObject newThread = new ParseObject(showTitle + typeSelected);
        newThread.put("title", postTitle);
        newThread.put("season", postSeason);
        newThread.put("episode", postEpisode);
        newThread.put("writer", postWriter);
        newThread.put("content", postContent);
        newThread.put("comments", postComment);
        newThread.saveEventually();

        //A new thread is saved as a parse object in the cloud under the category name as username's activity.
        ParseObject newUserThread = new ParseObject("My_Activity_"+ParseUser.getCurrentUser().getUsername());
        newUserThread.put("title", postTitle);
        newUserThread.put("season", postSeason);
        newUserThread.put("episode", postEpisode);
        newUserThread.put("writer", postWriter);
        newUserThread.put("content", postContent);
        newUserThread.put("comments", postComment);
        newUserThread.saveEventually();

        Intent intent = new Intent(this,Mainscreen.class);
        intent.addFlags(getIntent().getFlags());
        startActivity(intent);
    }
}