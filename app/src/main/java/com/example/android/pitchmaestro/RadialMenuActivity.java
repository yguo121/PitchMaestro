package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * The activity which calls the radial menu fragment.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
 */
public class RadialMenuActivity extends SingleFragmentActivity{

    /**
     * call the radial menu fragment .
     * @return RadialMenuFragment - the radial menu fragment.
     */
    @Override
    protected Fragment createFragment(){
        return new RadialMenuFragment();
    }

}
