package com.example.android.pitchmaestro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.anupcowkur.wheelmenu.WheelMenu;

/**
 * Created by stephen on 11/17/15.
 */
public class NoteWheelFragment extends Fragment{
    private WheelMenu wheelMenu;
    private TextView mWheelPosition;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note_basic, container, false);

        wheelMenu = (WheelMenu)v.findViewById(R.id.wheelMenu);
        wheelMenu.setDivCount(12);
        wheelMenu.setWheelImage(R.drawable.wheel);

        mWheelPosition = (TextView)v.findViewById(R.id.wheel_position);
        mWheelPosition.setText("selected: " + (wheelMenu.getSelectedPosition() + 1));
        wheelMenu.setWheelChangeListener(new WheelMenu.WheelChangeListener() {
            @Override
            public void onSelectionChange(int selectedPosition) {
                mWheelPosition.setText("selected: " + (selectedPosition + 1));
            }
        });

        return v;
    }

    public static NoteWheelFragment newInstance() {
        NoteWheelFragment f = new NoteWheelFragment();
        return f;
    }


}
