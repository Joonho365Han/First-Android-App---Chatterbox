package com.ec327.chatterbox.chatterbox;

import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class MainTabEpisode extends ListFragment {

    ArrayList<Thread> episodeThreads;
    String showTitle;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        refreshThreadList();
        return inflater.inflate(R.layout.main_episode_tab, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        episodeThreads = new ArrayList<>();
        ArrayAdapter<Thread> adapter = new ArrayAdapter<>(getActivity().getBaseContext(), R.layout.thread_template, episodeThreads);
        setListAdapter(adapter);
    }

    public void refreshThreadList() {

        //This line of loop specifies which type of show the mainscreen is displaying and at the same time
        //specifies the parse query which type of show thread to receive from the cloud.
        if (super.getActivity().getIntent().getFlags() == 1)
            showTitle = "Arrow_Thread";
        else if (super.getActivity().getIntent().getFlags() == 2)
            showTitle = "Daredevil_Thread";
        else if (super.getActivity().getIntent().getFlags() == 3)
            showTitle = "Flash_Thread";
        else if (super.getActivity().getIntent().getFlags()==4)
            showTitle = "FOB_Thread";
        else if (super.getActivity().getIntent().getFlags()==5)
            showTitle = "Game_of_Thrones_Thread";
        else if (super.getActivity().getIntent().getFlags()==6)
            showTitle = "Greys_Anatomy_Thread";
        else if (super.getActivity().getIntent().getFlags()==7)
            showTitle = "House_of_Cards_Thread";
        else if (super.getActivity().getIntent().getFlags()==8)
            showTitle = "Madmen_Thread";
        else if (super.getActivity().getIntent().getFlags()==9)
            showTitle = "How_to_Get_Away_With_Murder_Thread";
        else if (super.getActivity().getIntent().getFlags()==10)
            showTitle = "Once_Upon_A_Time_Thread";
        else if (super.getActivity().getIntent().getFlags()==11)
            showTitle = "Silicon_Valley_Thread";
        else if (super.getActivity().getIntent().getFlags()==12)
            showTitle = "The_100_Thread";

        //Retrieves the threads relating to this show and specifically about episodes.
        ParseQuery<ParseObject> query = ParseQuery.getQuery(showTitle + "_Episode");
        query.findInBackground(new FindCallback<ParseObject>() {

            @Override
            public void done(List<ParseObject> threadList, ParseException e) {
                if (e == null) {
                    // If there are results, update the list of posts
                    // and notify the adapter
                    episodeThreads.clear();
                    for (ParseObject threadObject : threadList) {
                        Thread thread = new Thread(threadObject.getObjectId(), threadObject.getString("title"), threadObject.getString("season"), threadObject.getString("episode"));
                        episodeThreads.add(thread);
                    }
                    ((ArrayAdapter<Thread>) getListAdapter()).notifyDataSetChanged();
                } else {
                    Log.d(getClass().getSimpleName(), "Error: " + e.getMessage());
                }
            }
        });
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        // Retrieves a specific thread info when the user clicks on a thread and goes to a screen where a thread is displayed.
        Intent intent = new Intent(super.getActivity(), ViewThread.class);
        Thread clicked = episodeThreads.get(position);
        String[] contents = {showTitle + "_Episode", clicked.getId()};
        intent.putExtra("Contents", contents);
        intent.addFlags(super.getActivity().getIntent().getFlags());
        startActivity(intent);
    }
}
