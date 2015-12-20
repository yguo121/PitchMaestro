package com.example.android.pitchmaestro;

/**
 * Created by stephen on 11/27/15.
 */
import java.io.IOException;
import java.util.ArrayList;


import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.android.pitchmaestro.radialmenu.RadialMenuItem;
import com.example.android.pitchmaestro.radialmenu.RadialMenuWidget;

public class RadialMenuFragment extends android.support.v4.app.Fragment {

    final MediaPlayer mp = new MediaPlayer();

    private RadialMenuWidget pieMenu;

    public RadialMenuItem menuItem, menuExpandItem, ChildItem1, ChildItem2, ChildItem3, ChildItem4, ChildItem5, ChildItem6, ChildItem7, ChildItem8, ChildItem9, ChildItem10, ChildItem11, ChildItem12;

    @SuppressWarnings("serial")
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.note_player_second, container, false);

        pieMenu = new RadialMenuWidget(getActivity());
        //pieMenu.show(v);
        pieMenu.setFocusable(true);
        pieMenu.setFocusableInTouchMode(true);

        menuItem = new RadialMenuItem("first","first");
        menuExpandItem = new RadialMenuItem("Expandable", "Expandable");
        ChildItem1 = new RadialMenuItem("C", "C");
        ChildItem2 = new RadialMenuItem("\u266F/\u266D", "\u266F/\u266D");
        ChildItem3 = new RadialMenuItem("D", "D");
        ChildItem4 = new RadialMenuItem("\u266F/\u266D", "\u266F/\u266D");
        ChildItem5 = new RadialMenuItem("E", "E");
        ChildItem6 = new RadialMenuItem("F", "F");
        ChildItem7 = new RadialMenuItem("\u266F/\u266D", "\u266F/\u266D");
        ChildItem8 = new RadialMenuItem("G", "G");
        ChildItem9 = new RadialMenuItem("\u266F/\u266D", "\u266F/\u266D");
        ChildItem10 = new RadialMenuItem("A", "A");
        ChildItem11 = new RadialMenuItem("\u266F/\u266D", "\u266F/\u266D");
        ChildItem12 = new RadialMenuItem("B", "B");

        //pieMenu.setDismissOnOutsideClick(true, menuLayout);
        pieMenu.setAnimationSpeed(0L);
        pieMenu.setBackgroundColor(0);
        pieMenu.setSourceLocation(180, 200);
        pieMenu.setIconSize(15, 30);
        pieMenu.setTextSize(13);
        pieMenu.setOutlineColor(Color.BLACK, 225);
        pieMenu.setInnerRingColor(0xAA66CC, 180);
        pieMenu.setOuterRingColor(0x0099CC, 180);
        pieMenu.setCenterCircle(ChildItem1);
        //pieMenu.setHeader("Test Menu", 20);

        pieMenu.addMenuEntry(new ArrayList<RadialMenuItem>() {{
            add(ChildItem1);
            add(ChildItem2);
            add(ChildItem3);
            add(ChildItem4);
            add(ChildItem5);
            add(ChildItem6);
            add(ChildItem7);
            add(ChildItem8);
            add(ChildItem9);
            add(ChildItem10);
            add(ChildItem11);
            add(ChildItem12);
        }});

        //pieMenu.addMenuEntry(menuItem);
        //pieMenu.addMenuEntry(menuExpandItem);
        pieMenu.setInnerRingRadius(100, 200);
        pieMenu.setTextSize(30);

        setNoteToItem(ChildItem1, "wheel01.wav");
        setNoteToItem(ChildItem2, "wheel02.wav");
        setNoteToItem(ChildItem3, "wheel03.wav");
        setNoteToItem(ChildItem4, "wheel04.wav");
        setNoteToItem(ChildItem5, "wheel05.wav");
        setNoteToItem(ChildItem6, "wheel06.wav");
        setNoteToItem(ChildItem7, "wheel07.wav");
        setNoteToItem(ChildItem8, "wheel08.wav");
        setNoteToItem(ChildItem9, "wheel09.wav");
        setNoteToItem(ChildItem10, "wheel10.wav");
        setNoteToItem(ChildItem11, "wheel11.wav");
        setNoteToItem(ChildItem12, "wheel12.wav");

        pieMenu.setHeader("Tap outside to exit", 50);

        Button mBasicButton = (Button)v.findViewById(R.id.note_basic);
        mBasicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieMenu.show(v);
            }
        });

        Button mAdvanced = (Button) v.findViewById(R.id.note_advanced);
        mAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NoteListActivityPlayer.class);
                startActivity(i);
            }
        });

        return v;
    }

    public static RadialMenuFragment newInstance() {
        RadialMenuFragment f = new RadialMenuFragment();
        return f;
    }

    // Enable each ChildItem to play a note when clicked
    private void setNoteToItem(RadialMenuItem childItem, final String string) {

        final MediaPlayer mp = new MediaPlayer();
        final RadialMenuItem temp1 = childItem;

        childItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                pieMenu.setCenterCircle(temp1);

                if (mp.isPlaying())
                {
                    mp.stop();
                }

                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getContext().getAssets().openFd(string);
                    mp.setDataSource(afd.getFileDescriptor(),afd.getStartOffset(),afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        mp.release();
                    }
                });
            }
        });
    }



}