package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * The activity which calls the note list player fragment.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 */
public class NoteListActivityPlayer extends SingleFragmentActivity{

    /**
     * call the note list playerfragment .
     * @return NoteListFragmentPlayer - the note list player fragment.
     */
    @Override
    protected Fragment createFragment(){
        return new NoteListFragmentPlayer();
    }

}
