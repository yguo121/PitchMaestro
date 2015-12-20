package com.example.android.pitchmaestro;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * This class create two tabs for the piano and the pipe.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
 */
public class NoteListFragmentPlayer extends Fragment {

    private FragmentTabHost mTabHost;       // the host of tabs.

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mTabHost = new FragmentTabHost(getActivity());
        mTabHost.setup(getActivity(), getChildFragmentManager(), R.id.note_list_pipe);

        mTabHost.addTab(mTabHost.newTabSpec("Pitch Pipe").setIndicator("Pitch Pipe"), NoteListFragmentPipe.class, null);    //pipe tab
        mTabHost.addTab(mTabHost.newTabSpec("Piano").setIndicator("Piano"), NoteListFragmentPiano.class, null);             // piano tab

        return mTabHost;
    }

    /**
     * The ondestory view.
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mTabHost = null;
    }
}