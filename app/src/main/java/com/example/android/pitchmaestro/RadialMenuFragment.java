package com.example.android.pitchmaestro;

/**
 * Created by stephen on 11/27/15.
 */
import java.io.IOException;
import java.util.ArrayList;

import com.widget.radialmenu.RadialMenuItem;
import com.widget.radialmenu.RadialMenuWidget;


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

public class RadialMenuFragment extends android.support.v4.app.Fragment {

    final MediaPlayer mp = new MediaPlayer();

    private RadialMenuWidget pieMenu;

    public RadialMenuItem menuItem, menuExpandItem, ChildItem1, ChildItem2, ChildItem3, ChildItem4, ChildItem5, ChildItem6, ChildItem7, ChildItem8, ChildItem9, ChildItem10, ChildItem11, ChildItem12;

    @SuppressWarnings("serial")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.radial, container, false);

        pieMenu = new RadialMenuWidget(getActivity());
        pieMenu.show(v);

        menuItem = new RadialMenuItem("first","first");
        menuExpandItem = new RadialMenuItem("Expandable", "Expandable");
        ChildItem1 = new RadialMenuItem("C", "C");
        ChildItem2 = new RadialMenuItem("#/b", "#/b");
        ChildItem3 = new RadialMenuItem("D", "D");
        ChildItem4 = new RadialMenuItem("#/b", "#/b");
        ChildItem5 = new RadialMenuItem("E", "E");
        ChildItem6 = new RadialMenuItem("F", "F");
        ChildItem7 = new RadialMenuItem("#/b", "#/b");
        ChildItem8 = new RadialMenuItem("G", "G");
        ChildItem9 = new RadialMenuItem("#/b", "#/b");
        ChildItem10 = new RadialMenuItem("A", "A");
        ChildItem11 = new RadialMenuItem("#/b", "#/b");
        ChildItem12 = new RadialMenuItem("B", "B");

        //pieMenu.setDismissOnOutsideClick(true, menuLayout);
        pieMenu.setAnimationSpeed(0L);
        pieMenu.setSourceLocation(180, 200);
        pieMenu.setIconSize(15, 30);
        pieMenu.setTextSize(13);
        pieMenu.setOutlineColor(Color.BLACK, 225);
        pieMenu.setInnerRingColor(0xAA66CC, 180);
        pieMenu.setOuterRingColor(0x0099CC, 180);
        //pieMenu.setHeader("Test Menu", 20);
        pieMenu.setCenterCircle(ChildItem1);

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

        // final MediaPlayer mp = new MediaPlayer();

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
        /*
        ChildItem1.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
                if (mp.isPlaying()) {
                    mp.stop();
                }

                try {

                    mp.reset();
                    AssetFileDescriptor afd;
                    afd = getContext().getAssets().openFd("wheel01.wav");
                    mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                    mp.prepare();
                    mp.start();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

        */
        return v;
    }

    public static RadialMenuFragment newInstance() {
        RadialMenuFragment f = new RadialMenuFragment();
        return f;
    }

    // Enable each ChildItem to play a note when clicked
    private void setNoteToItem(RadialMenuItem childItem, final String string) {
        final MediaPlayer mp = new MediaPlayer();

        childItem.setOnMenuItemPressed(new RadialMenuItem.RadialMenuItemClickListener() {
            @Override
            public void execute() {
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
            }
        });
    }

}