package com.example.android.pitchmaestro;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

import be.tarsos.dsp.AudioDispatcher;
import be.tarsos.dsp.AudioEvent;
import be.tarsos.dsp.AudioProcessor;
import be.tarsos.dsp.io.android.AudioDispatcherFactory;
import be.tarsos.dsp.pitch.PitchDetectionHandler;
import be.tarsos.dsp.pitch.PitchDetectionResult;
import be.tarsos.dsp.pitch.PitchProcessor;

/**
 * Created by stephen on 11/17/15.
 */
public class PitchDetectorFragment extends Fragment{

    private Button mNegRed;
    private Button mNegYellow;
    private Button mNeuGreen;
    private Button mPosYellow;
    private Button mPosRed;
    private TextView mNoteDisplay;

    private String[] mNoteArray = new String[96];

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_pitch_detector, container, false);

        mNegRed = (Button) v.findViewById(R.id.negative_red);
        mNegRed.setBackgroundColor(getResources().getColor(R.color.trans_red));

        mNegYellow = (Button) v.findViewById(R.id.negative_yellow);
        mNegYellow.setBackgroundColor(getResources().getColor(R.color.trans_yellow));

        mNeuGreen = (Button) v.findViewById(R.id.neutral_green);
        mNeuGreen.setBackgroundColor(getResources().getColor(R.color.trans_green));

        mPosYellow = (Button) v.findViewById(R.id.positive_yellow);
        mPosYellow.setBackgroundColor(getResources().getColor(R.color.trans_yellow));

        mPosRed = (Button) v.findViewById(R.id.positive_red);
        mPosRed.setBackgroundColor(getResources().getColor(R.color.trans_red));

        for (int i=0, j=1; i<mNoteArray.length; i+=12, j++) {
            String k = Integer.toString(j);
            mNoteArray[i] = "C"+k;
            mNoteArray[i+1] = "C#"+k+"/Db"+k;
            mNoteArray[i+2] = "D"+k;
            mNoteArray[i+3] = "D#"+k+"/Eb"+k;
            mNoteArray[i+4] = "E"+k;
            mNoteArray[i+5] = "F"+k;
            mNoteArray[i+6] = "F#"+k+"/Gb"+k;
            mNoteArray[i+7] = "G"+k;
            mNoteArray[i+8] = "G#"+k+"/Ab"+k;
            mNoteArray[i+9] = "A"+k;
            mNoteArray[i+10] = "A#"+k+"/Bb"+k;
            mNoteArray[i+11] = "B"+k;
        }

        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);

        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult result,AudioEvent e) {
                final float pitchInHz = result.getPitch();
                if(getActivity()==null)
                    return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNoteDisplay = (TextView) v.findViewById(R.id.note_display);
                        mNoteDisplay.setText(getNote(pitchInHz)+ ", " + pitchInHz);
                    }
                });
            }
        };
        AudioProcessor p = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(p);
        new Thread(dispatcher,"Audio Dispatcher").start();

        return v;
    }

    private String getNote(float pitch) {
        double d = pitch;
        double e = (Math.log(d)-2.79)/0.057;
        float f = (float) e;
        int i = Math.round(f);
        return mNoteArray[i];
    }
}