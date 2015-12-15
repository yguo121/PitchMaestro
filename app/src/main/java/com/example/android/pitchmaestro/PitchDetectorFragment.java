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

    private String leftRed      = "LR";
    private String leftYellow   = "LY";
    private String midGreen     = "MG";
    private String rightYellow  = "RY";
    private String rightRed     = "RR";

    private Button mNegRed;
    private Button mNegYellow;
    private Button mNeuGreen;
    private Button mPosYellow;
    private Button mPosRed;
    private TextView mNoteDisplay;
    private TextView mPitchDisplay;
    private String mLast = "Start";

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
                final int pitchInHzInInteger = Math.round(pitchInHz);
                //setColor(getDifference(pitchInHz));
                //mPosYellow.setBackgroundColor(getResources().getColor(R.color.yellow));
                if(getActivity()==null)
                    return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNoteDisplay = (TextView) v.findViewById(R.id.note_display);
                        mPitchDisplay = (TextView) v.findViewById(R.id.pitch_display);
                        if (pitchInHz != -1) {

                            mNoteDisplay.setText(getNote(pitchInHz));
                            mPitchDisplay.setText(pitchInHzInInteger + " Hz");
                            setColor(getDifference(pitchInHz));
                            mLast = getNote(pitchInHz);
                        } else {
                            //TODO Add to string.xml later
                            resetColor();
                            mNoteDisplay.setText(mLast);
                        }
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
        double p = (double) pitch;              // Pitch in Hertz
        double e = (Math.log(p)-2.79)/0.058;
        int i = Math.round((float) e);          // Index in the mNoteArray

        return mNoteArray[i];
    }

    private String getDifference(float pitch) {

        /*
        double step = 1.0/12.0;
        double expStep = Math.pow(2.0, step);
        double cNoteA4 = 12.0 * Math.log(440.0) / Math.log(2);  // Log-value pitch of Note A4
        int indNoteA4 = 57;                                     // Index of Note A4 in the mNoteArray
        */

        double p = (double) pitch;                              // Pitch in Hertz
        double e = (Math.log(p)-2.79)/0.058;
        int ind = Math.round((float) e);                        // Index in the mNoteArray
        double lp = 2.79 + (ind*0.058);
        double diff = Math.log(pitch) - lp;

        /*
        double c = (Math.log(p)/Math.log(expStep));             // Log-value pitch
        double cNote = step * (ind - indNoteA4) + cNoteA4;      // Nearest Note in log-value
        double cDiff = c - cNote;
        */


        if (diff > 0.0174) {
            return rightRed;
        } else if (diff > 0.0058) {
            return rightYellow;
        } else if (diff > -0.0058) {
            return midGreen;
        } else if (diff > -0.0174) {
            return leftYellow;
        } else if (diff > -0.029) {    // 0.3 <= cDiff <= 0.5
            return leftRed;
        } else {
            return "";
        }

    }

    private void setColor(String color){
        if (color == "") {
            resetColor();
        }

        if (color == leftRed){
            resetColor();
            mNegRed.setBackgroundColor(getResources().getColor(R.color.red));
        }

        if (color == leftYellow){
            resetColor();
            mNegYellow.setBackgroundColor(getResources().getColor(R.color.yellow));
        }

        if (color == midGreen){
            resetColor();
            mNeuGreen.setBackgroundColor(getResources().getColor(R.color.green));
        }

        if (color == rightYellow){
            resetColor();
            mPosYellow.setBackgroundColor(getResources().getColor(R.color.yellow));
        }

        if (color == rightRed){
            resetColor();
            mPosRed.setBackgroundColor(getResources().getColor(R.color.red));
        }
    }

    private void resetColor() {
        mNegRed.setBackgroundColor(getResources().getColor(R.color.trans_red));
        mNegYellow.setBackgroundColor(getResources().getColor(R.color.trans_yellow));
        mNeuGreen.setBackgroundColor(getResources().getColor(R.color.trans_green));
        mPosYellow.setBackgroundColor(getResources().getColor(R.color.trans_yellow));
        mPosRed.setBackgroundColor(getResources().getColor(R.color.trans_red));
    }

}