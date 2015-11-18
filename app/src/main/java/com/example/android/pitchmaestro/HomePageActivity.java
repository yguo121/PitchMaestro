package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * Created by stephen on 11/17/15.
 */
public class HomePageActivity extends SingleFragmentActivity{

    @Override
    protected Fragment createFragment(){
        return new HomePageFragment();
    }

}
