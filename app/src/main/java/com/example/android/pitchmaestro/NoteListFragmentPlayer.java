package com.example.android.pitchmaestro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class NoteListFragmentPlayer extends Fragment {
    private FragmentTabHost mTabHost;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.note_list_choir);

        mTabHost.addTab(mTabHost.newTabSpec("String").setIndicator("String"), NoteListFragmentChoir.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Choir").setIndicator("Choir"), NoteListFragmentPiano.class, null);
        mTabHost.addTab(mTabHost.newTabSpec("Piano").setIndicator("Piano"), NoteListFragmentPiano.class, null);

        return mTabHost;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }

    public static NoteListFragmentPlayer newInstance() {
        NoteListFragmentPlayer f = new NoteListFragmentPlayer();
        return f;
    }
}