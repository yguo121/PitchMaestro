package com.example.android.pitchmaestro;

/**
 * Created by stephen on 11/27/15.
 */
import java.util.ArrayList;

import com.widget.radialmenu.RadialMenuItem;
import com.widget.radialmenu.RadialMenuWidget;


import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class RadialMenuFragment extends android.support.v4.app.Fragment {

    private RadialMenuWidget pieMenu;


    public RadialMenuItem menuItem, menuExpandItem, ChildItem1, ChildItem2, ChildItem3, ChildItem4, ChildItem5, ChildItem6, ChildItem7, ChildItem8, ChildItem9, ChildItem10, ChildItem11, ChildItem12;

    @SuppressWarnings("serial")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View vie = inflater.inflate(R.layout.radial, container, false);

        pieMenu = new RadialMenuWidget(getActivity());
        pieMenu.show(vie);

        menuItem = new RadialMenuItem("first","first");
        menuExpandItem = new RadialMenuItem("Expandable", "Expandable");
        ChildItem1 = new RadialMenuItem("B", "B");
        ChildItem2 = new RadialMenuItem("C", "C");
        ChildItem3 = new RadialMenuItem("#/b", "#/b");
        ChildItem4 = new RadialMenuItem("D", "D");
        ChildItem5 = new RadialMenuItem("#/b", "#/b");
        ChildItem6 = new RadialMenuItem("E", "E");
        ChildItem7 = new RadialMenuItem("F", "F");
        ChildItem8 = new RadialMenuItem("#/b", "#/b");
        ChildItem9 = new RadialMenuItem("G", "G");
        ChildItem10 = new RadialMenuItem("#/b", "#/b");
        ChildItem11 = new RadialMenuItem("A", "A");
        ChildItem12 = new RadialMenuItem("#/b", "#/b");

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
            add(ChildItem12);}});

        //pieMenu.addMenuEntry(menuItem);
        //pieMenu.addMenuEntry(menuExpandItem);
        pieMenu.setInnerRingRadius(100, 200);
        pieMenu.setTextSize(30);

        return vie;
    }

    public static RadialMenuFragment newInstance() {
        RadialMenuFragment f = new RadialMenuFragment();
        return f;
    }
}