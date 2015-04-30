package com.ec327.chatterbox.chatterbox;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.parse.Parse;
import com.parse.ParseUser;

import java.util.ArrayList;

public class MyShows extends FragmentActivity {

    FragmentManager fragmentManager;
    FragmentTransaction fragmentTransaction;
    ArrayList<Integer> std;

    /* This is the Constructor in context of Java for the Android app. */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myshows);

        fragmentManager = getFragmentManager();
        fragmentTransaction = fragmentManager.beginTransaction();

        //Parse is initiallized not just to exchange data but also to reconnect in case of crash.
        Parse.initialize(this, "sIIPDbEWnnRETu0XlKQL6QMER34bBR3ZPNV2Ibmu", "OGFvOpzYbYNsb4n9xEHIaT8vdiZFvXZOXxFAzer4");

        //Lists the different types of preallocated "containers" the show button will be placed int.
        int[] listOfFrames = {R.id.myshows_GOT, R.id.myshows_greysAnatomy, R.id.myshows_daredevil, R.id.myshows_flash, R.id.myshows_100,
                R.id.myshows_once, R.id.myshows_FOB, R.id.myshows_htgawm, R.id.myshows_silliconValley, R.id.myshows_madmen, R.id.myshows_HOC,
                R.id.myshows_arrow};

        //Pre-initializes the show buttons.
        Arrow fragment = new Arrow();
        Daredevil daredevil = new Daredevil();
        Flash flash = new Flash();
        FOB fob = new FOB();
        GameOfThrones gameOfThrones = new GameOfThrones();
        GreysAnatomy greysAnatomy = new GreysAnatomy();
        HouseOfCards houseOfCards = new HouseOfCards();
        MadMen madMen = new MadMen();
        Murder murder = new Murder();
        OnceUponATime onceUponATime = new OnceUponATime();
        SiliconValley siliconValley = new SiliconValley();
        The100 the100 = new The100();

        //Removes all the buttons before adding any so that they do not overlap.
            fragmentTransaction.remove(fragment);
            fragmentTransaction.remove(daredevil);
            fragmentTransaction.remove(flash);
            fragmentTransaction.remove(fob);
            fragmentTransaction.remove(gameOfThrones);
            fragmentTransaction.remove(greysAnatomy);
            fragmentTransaction.remove(houseOfCards);
            fragmentTransaction.remove(madMen);
            fragmentTransaction.remove(murder);
            fragmentTransaction.remove(onceUponATime);
            fragmentTransaction.remove(siliconValley);
            fragmentTransaction.remove(the100);

        //Gets the user specified show list settings. This list is updated every tim e the user updates list via the "Addshows" activity.
            std = (ArrayList<Integer>) ParseUser.getCurrentUser().get("Choices");
            int index = 0;

        //Add the show buttons.
            if (std.contains(5)) {
                fragmentTransaction.add(listOfFrames[index], gameOfThrones);
                index++;
            }
            if (std.contains(6)) {
                fragmentTransaction.add(listOfFrames[index], greysAnatomy);
                index++;
            }
            if (std.contains(2)) {
                fragmentTransaction.add(listOfFrames[index], daredevil);
                index++;
            }
            if (std.contains(3)) {
                fragmentTransaction.add(listOfFrames[index], flash);
                index++;
            }
            if (std.contains(12)) {
                fragmentTransaction.add(listOfFrames[index], the100);
                index++;
            }
            if (std.contains(10)) {
                fragmentTransaction.add(listOfFrames[index], onceUponATime);
                index++;
            }
            if (std.contains(4)) {
                fragmentTransaction.add(listOfFrames[index], fob);
                index++;
            }
            if (std.contains(9)) {
                fragmentTransaction.add(listOfFrames[index], murder);
                index++;
            }
            if (std.contains(11)) {
                fragmentTransaction.add(listOfFrames[index], siliconValley);
                index++;
            }
            if (std.contains(8)) {
                fragmentTransaction.add(listOfFrames[index], madMen);
                index++;
            }
            if (std.contains(7)) {
                fragmentTransaction.add(listOfFrames[index], houseOfCards);
                index++;
            }
            if (std.contains(1)) {
                fragmentTransaction.add(listOfFrames[index], fragment);
            }

            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_myshows, menu);
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
        } else if (id == R.id.action_myActivity) {
            toMyActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void toAddShows() {
        Intent intent = new Intent(this, AddShows.class);
        intent.putIntegerArrayListExtra("Choices",std);
        startActivity(intent);
    }

    private void toMyActivity() {
        Intent intent = new Intent(this, MyActivity.class);
        intent.putIntegerArrayListExtra("Choices",std);
        startActivity(intent);
    }

    //Moves to the Mainscreen activity.
    public void openForum(View view) {
        Intent toMain = new Intent(this, Mainscreen.class);

        //This line of loop specifies which type of show the user chose and passes the info
        //to the Mainscreen screen template so that the show icon shows on top.
        if (view.getTag().toString().equals("1"))
            toMain.addFlags(1);
        else if (view.getTag().toString().equals("2"))
            toMain.addFlags(2);
        else if (view.getTag().toString().equals("3"))
            toMain.addFlags(3);
        else if (view.getTag().toString().equals("4"))
            toMain.addFlags(4);
        else if (view.getTag().toString().equals("5"))
            toMain.addFlags(5);
        else if (view.getTag().toString().equals("6"))
            toMain.addFlags(6);
        else if (view.getTag().toString().equals("7"))
            toMain.addFlags(7);
        else if (view.getTag().toString().equals("8"))
            toMain.addFlags(8);
        else if (view.getTag().toString().equals("9"))
            toMain.addFlags(9);
        else if (view.getTag().toString().equals("10"))
            toMain.addFlags(10);
        else if (view.getTag().toString().equals("11"))
            toMain.addFlags(11);
        else if (view.getTag().toString().equals("12"))
            toMain.addFlags(12);

        toMain.putIntegerArrayListExtra("Choices",std);
        startActivity(toMain);
    }
}