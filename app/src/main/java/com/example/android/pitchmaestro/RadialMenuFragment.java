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

/**
 * This is the Radial Menu and it has been divided into 12 parts and it has two buttons.
 * The Radial Menu is a widget designed by strider2023@gmail.com please check
 * https://github.com/strider2023/Radial-Menu-Widget-Android.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
 */
public class RadialMenuFragment extends android.support.v4.app.Fragment {

    private RadialMenuWidget pieMenu;       // the radial menu.

    public RadialMenuItem menuItem, menuExpandItem, ChildItem1, ChildItem2, ChildItem3, ChildItem4, ChildItem5, ChildItem6, ChildItem7, ChildItem8, ChildItem9, ChildItem10, ChildItem11, ChildItem12;  // the 12 buttons of the menu.

    @SuppressWarnings("serial")
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.note_player_second, container, false);

        pieMenu = new RadialMenuWidget(getActivity());      // the view of the fragment.
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
        // construct buttons and give them names.

        pieMenu.setAnimationSpeed(0L);
        pieMenu.setBackgroundColor(0);
        pieMenu.setSourceLocation(70, 200);
        pieMenu.setIconSize(15, 30);
        pieMenu.setTextSize(13);
        pieMenu.setOutlineColor(Color.BLACK, 225);
        pieMenu.setInnerRingColor(0X79243d, 255);
        pieMenu.setOuterRingColor(0X7fffd3, 180);
        pieMenu.setCenterCircle(ChildItem1);
        pieMenu.setCenterLocation(375,600);
        pieMenu.setHeaderColors(0X000000, 0Xffffff , 0Xffffff, 0);
        // set the attribute of the radial menu.

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
        // add child items into the radial menu.

        pieMenu.setInnerRingRadius(100, 250);
        pieMenu.setCenterCircleRadius(80);
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
        // bind audio files with the buttons.

        pieMenu.setHeader("Tap outside to exit", 50);
        // set the header of the radial menu.

        Button mBasicButton = (Button)v.findViewById(R.id.note_basic);
        mBasicButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pieMenu.show(v);
            }
        });
        // set the click listener of the basic button.

        Button mAdvanced = (Button) v.findViewById(R.id.note_advanced);
        mAdvanced.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NoteListActivityPlayer.class);
                startActivity(i);
            }
        });
        // set the click listener of the advanced button.

        return v;
    }


    // Enable each ChildItem to play a note when clicked
    private void setNoteToItem(RadialMenuItem childItem, final String string) {

        //final MediaPlayer mp = new MediaPlayer();
        final RadialMenuItem temp1 = childItem;

        childItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                pieMenu.setCenterCircle(temp1);
                final MediaPlayer mp = new MediaPlayer();
                if (mp.isPlaying())
                {
                    mp.stop();
                }

                try {
                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getContext().getAssets().openFd(string);
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
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