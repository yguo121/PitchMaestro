package com.example.android.pitchmaestro;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

/**
 * Created by stephen on 11/3/15.
 */
public class HomePageFragment extends Fragment {

    private ImageButton mPitchDetector;
    private ImageButton mNotePlayer;
    private ImageButton mVoicePartTest;
    private ImageButton mPitchQuiz;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.home_page, container, false);

        mPitchDetector = (ImageButton) v.findViewById(R.id.pitch_detector);
        mPitchDetector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), PitchDetectorActivity.class);
                startActivity(i);
            }
        });

        mNotePlayer = (ImageButton) v.findViewById(R.id.note_player);
        mNotePlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NotePagerActivity.class);
                HomePageFragment.this.startActivity(i);
            }
        });

        mPitchQuiz = (ImageButton) v.findViewById(R.id.pitch_quiz);
        mPitchQuiz.setEnabled(false);

        mVoicePartTest = (ImageButton) v.findViewById(R.id.voice_part_test);
        mVoicePartTest.setEnabled(false);


        return v;
    }

}
