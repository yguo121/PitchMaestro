package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * The activity which calls the home page fragment.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 */
public class HomePageActivity extends SingleFragmentActivity{

    /**
     * call the fragment .
     * @return HomePageFragment - the home page fragment.
     */
    @Override
    protected Fragment createFragment(){
        return new HomePageFragment();
    }

}
