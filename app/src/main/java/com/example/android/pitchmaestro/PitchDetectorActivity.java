package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * The activity which calls the pitch detector fragment.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
 */

public class PitchDetectorActivity extends SingleFragmentActivity {

    /**
     * call the pitch detector fragment .
     * @return PitchDetectorFragment - the pitch detector fragment.
     */
    @Override
    protected Fragment createFragment(){
        return new PitchDetectorFragment();
    }
}