package com.example.android.pitchmaestro;

import android.support.v4.app.Fragment;

/**
 * The activity which calls the home page fragment.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
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
