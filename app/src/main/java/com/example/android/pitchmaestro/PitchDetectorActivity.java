package com.example.android.pitchmaestro;

        import android.support.v4.app.Fragment;

public class PitchDetectorActivity extends SingleFragmentActivity {

    @Override
    protected Fragment createFragment(){
        return new PitchDetectorFragment();
    }
}