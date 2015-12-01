package com.example.android.pitchmaestro;

import android.app.TabActivity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;
import android.support.v7.internal.widget.TintTypedArray;

/**
 * Created by stephen on 11/28/15.
 */
public class NoteListPlayer extends TabActivity
{
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_list_player);


        // create the TabHost that will contain the Tabs
        TabHost tabHost = (TabHost)findViewById(android.R.id.tabhost);


        TabHost.TabSpec tab1 = tabHost.newTabSpec("First Tab");
        TabHost.TabSpec tab2 = tabHost.newTabSpec("Second Tab");
        TabHost.TabSpec tab3 = tabHost.newTabSpec("Third tab");

        // Set the Tab name and Activity
        // that will be opened when particular Tab will be selected
        tab1.setIndicator("Choir");
        tab1.setContent(new Intent(this,NoteListActivityChoir.class));

        tab2.setIndicator("String");
        tab2.setContent(new Intent(this,NoteListActivityString.class));

        tab3.setIndicator("Piano");
        tab3.setContent(new Intent(this,NoteListActivityPiano.class));

        /** Add the tabs  to the TabHost to display. */
        tabHost.addTab(tab1);
        tabHost.addTab(tab2);
        tabHost.addTab(tab3);

    }
}