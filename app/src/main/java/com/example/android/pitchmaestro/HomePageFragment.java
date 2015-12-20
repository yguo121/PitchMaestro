package com.example.android.pitchmaestro;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

/**
 * The is the home page fragment which contains two buttons.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 */
public class HomePageFragment extends Fragment {

    private ImageButton mPitchDetector;     // the button of the pitch detector.
    private ImageButton mNotePlayer;        // the button of the note player.


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_page, container, false);


        mPitchDetector = (ImageButton) v.findViewById(R.id.pitch_detector);
        mPitchDetector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //the click listener of the pitch detector button.
                Intent i = new Intent(getActivity(), PitchDetectorActivity.class);
                startActivity(i);
            }
        });

        mNotePlayer = (ImageButton) v.findViewById(R.id.note_player);
        mNotePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {       //the click listener of the note player button.
                Intent i = new Intent(getActivity(), RadialMenuActivity.class);
                HomePageFragment.this.startActivity(i);
            }
        });

        return v;
    }

}
