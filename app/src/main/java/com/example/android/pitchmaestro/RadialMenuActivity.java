package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * The activity which calls the radial menu fragment.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
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
