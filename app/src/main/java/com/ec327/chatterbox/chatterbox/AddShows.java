package com.ec327.chatterbox.chatterbox;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.CheckBox;

import com.parse.Parse;
import com.parse.ParseUser;

import java.util.ArrayList;

public class AddShows extends Activity {

    ArrayList<Integer> check;

    Integer Flash;
    Integer Dare;
    Integer GOT;
    Integer FOB;
    Integer Grey;
    Integer Arrow;
    Integer Murder;
    Integer HOC;
    Integer Mad;
    Integer Once;
    Integer SV;
    Integer The100;

    /* This is the Constructor in context of Java for the Android app. */
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addshows);

        //Parse is initiallized not just to exchange data but also to reconnect in case of crash.
        Parse.initialize(this, "sIIPDbEWnnRETu0XlKQL6QMER34bBR3ZPNV2Ibmu", "OGFvOpzYbYNsb4n9xEHIaT8vdiZFvXZOXxFAzer4");

        check = new ArrayList<>(12);

        Flash = 3;
        Dare = 2;
        GOT = 5;
        FOB = 4;
        Grey = 6;
        Arrow = 1;
        Murder = 9;
        HOC = 7;
        Mad = 8;
        Once = 10;
        SV = 11;
        The100 = 12;

        //These if statements check which shows users have already added into their myshows list and makes the
        //check box for user choice already checked so that users do not add the same show twice.
        if(ParseUser.getCurrentUser().has("Choices")){
            ArrayList<Integer> recheck = (ArrayList<Integer>) ParseUser.getCurrentUser().get("Choices");

            if (recheck.contains(5)) {
                CheckBox toggleGOT = (CheckBox) findViewById(R.id.toggle_GOT);
                toggleGOT.setChecked(true);
                check.add(GOT);
            }
            if (recheck.contains(6)) {
                CheckBox toggleGrey = (CheckBox) findViewById(R.id.toggle_grey);
                toggleGrey.setChecked(true);
                check.add(Grey);
            }
            if (recheck.contains(2)) {
                CheckBox toggleDare = (CheckBox) findViewById(R.id.toggle_dare);
                toggleDare.setChecked(true);
                check.add(Dare);
            }
            if (recheck.contains(3)) {
                CheckBox toggleFlash = (CheckBox) findViewById(R.id.toggle_flash);
                toggleFlash.setChecked(true);
                check.add(Flash);
            }
            if (recheck.contains(12)) {
                CheckBox toggle100 = (CheckBox) findViewById(R.id.toggle_100);
                toggle100.setChecked(true);
                check.add(The100);
            }
            if (recheck.contains(10)) {
                CheckBox toggleOnce = (CheckBox) findViewById(R.id.toggle_once);
                toggleOnce.setChecked(true);
                check.add(Once);
            }
            if (recheck.contains(4)) {
                CheckBox toggleFOB = (CheckBox) findViewById(R.id.toggle_fob);
                toggleFOB.setChecked(true);
                check.add(FOB);
            }
            if (recheck.contains(9)) {
                CheckBox toggleMurder = (CheckBox) findViewById(R.id.toggle_murder);
                toggleMurder.setChecked(true);
                check.add(Murder);
            }
            if (recheck.contains(11)) {
                CheckBox toggleSilicon = (CheckBox) findViewById(R.id.toggle_silicon);
                toggleSilicon.setChecked(true);
                check.add(SV);
            }
            if (recheck.contains(8)) {
                CheckBox toggleMad = (CheckBox) findViewById(R.id.toggle_mad);
                toggleMad.setChecked(true);
                check.add(Mad);
            }
            if (recheck.contains(7)) {
                CheckBox toggleHOC = (CheckBox) findViewById(R.id.toggle_hoc);
                toggleHOC.setChecked(true);
                check.add(HOC);
            }
            if (recheck.contains(1)) {
                CheckBox toggleArrow = (CheckBox) findViewById(R.id.toggle_arrow);
                toggleArrow.setChecked(true);
                check.add(Arrow);
            }
        }
    }

    /* This function is called in response to the 'done' button of the AddShows activity.
    * It basically returns the user to the  'Shows' screen, but in contrast to the back button
    * or the 'up' button, this function implements the change made in the list of shows to add.*/
    public void addShowsToList(View view){
        Intent toMyShows = new Intent(this, MyShows.class);
        //Maybe in the future we'll add these show lists into the could so user can retrive it on myshows screen
        startActivity(toMyShows);

        if(ParseUser.getCurrentUser().has("Choices"))
            ParseUser.getCurrentUser().remove("Choices");
        ParseUser.getCurrentUser().put("Choices", check);
        ParseUser.getCurrentUser().saveEventually();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_addshows, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_myActivity) {
            toMyActivity();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void addGOT(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(GOT);
        }else{
            check.remove(check.indexOf(GOT));
        }
    }

    public void addGreysAnatomy(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(Grey);
        }else{
            check.remove(check.indexOf(Grey));
        }
    }

    public void addDaredevil(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(Dare);
        }else{
            check.remove(check.indexOf(Dare));
        }
    }

    public void addFlash(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(Flash);
        }else{
            check.remove(check.indexOf(Flash));
        }
    }

    public void add100(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(The100);
        }else{
            check.remove(check.indexOf(The100));
        }
    }

    public void addOnce(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(Once);
        }else{
            check.remove(check.indexOf(Once));
        }
    }

    public void addFOB(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(FOB);
        }else{
            check.remove(check.indexOf(FOB));
        }
    }

    public void addMurder(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(Murder);
        }else{
            check.remove(check.indexOf(Murder));
        }
    }

    public void addSilicon(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(SV);
        }else{
            check.remove(check.indexOf(SV));
        }
    }

    public void addMad(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(Mad);
        }else{
            check.remove(check.indexOf(Mad));
        }
    }

    public void addHOC(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(HOC);
        }else{
            check.remove(check.indexOf(HOC));
        }
    }

    public void addArrow(View view) {
        // Is the view now checked?
        boolean checked = ((CheckBox) view).isChecked();

        if (checked){
            check.add(Arrow);
        }else{
            check.remove(check.indexOf(Arrow));
        }
    }

    /* This function brings the user to the MyActivity Activity*/
    private void toMyActivity() {
        Intent intent = new Intent(this,MyActivity.class);
        startActivity(intent);
    }
}
