package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * Created by stephen on 12/1/15.
 */
public class RadialMenuActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        return new RadialMenuFragment();
    }

}