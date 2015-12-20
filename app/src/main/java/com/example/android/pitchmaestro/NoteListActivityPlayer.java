package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * The activity which calls the note list player fragment.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
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
