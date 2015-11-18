package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * Created by stephen on 11/16/15.
 */
public class NoteListActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        return new NoteListFragment();
    }

}
