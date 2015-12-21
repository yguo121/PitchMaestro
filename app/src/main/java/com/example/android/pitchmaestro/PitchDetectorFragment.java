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
 * This class contains the piano notes list.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
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
        final String[] octaves = {"\u2080", "\u2081", "\u2082", "\u2083",
                "\u2084", "\u2085", "\u2086", "\u2087", "\u2088"};

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

        for (int i=0, j=0; i<mNoteArray.length; i+=12, j++) {
            mNoteArray[i]       = "C"+ octaves[j];
            mNoteArray[i+1]     = "C\u266F"+ octaves[j] +"/D\u266D"+ octaves[j];
            mNoteArray[i+2]     = "D"+ octaves[j];
            mNoteArray[i+3]     = "D\u266F"+ octaves[j] +"/E\u266D"+ octaves[j];
            mNoteArray[i+4]     = "E"+ octaves[j];
            mNoteArray[i+5]     = "F"+ octaves[j];
            mNoteArray[i+6]     = "F\u266F"+ octaves[j] +"/G\u266D"+ octaves[j];
            mNoteArray[i+7]     = "G"+ octaves[j];
            mNoteArray[i+8]     = "G\u266F"+ octaves[j] +"/A\u266D"+ octaves[j];
            mNoteArray[i+9]     = "A"+ octaves[j];
            mNoteArray[i+10]    = "A\u266F"+ octaves[j] +"/B\u266D"+ octaves[j];
            mNoteArray[i+11]    = "B"+ octaves[j];
        }

        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050, 1024, 0);
        //connects AudioDispatcher to Android device microphone

        PitchDetectionHandler pdh = new PitchDetectionHandler() {
            @Override
            public void handlePitch(PitchDetectionResult result,AudioEvent e) {
                final float pitchInHz = result.getPitch();
                final int pitchInHzInInteger = Math.round(pitchInHz);

                if(getActivity()==null)
                    return;
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNoteDisplay = (TextView) v.findViewById(R.id.note_display);
                        mPitchDisplay = (TextView) v.findViewById(R.id.pitch_display);

                        if (pitchInHz != -1) { //pitch is detected
                            mNoteDisplay.setText(getNote(pitchInHz));
                            mPitchDisplay.setText(pitchInHzInInteger + " Hz");
                            setColor(getDifference(pitchInHz));
                            mLast = getNote(pitchInHz);
                        } else { //no pitch detected
                            resetColor();
                            mNoteDisplay.setText(mLast); //displays last detected pitch
                        }
                    }
                });
            }
        };
        AudioProcessor p = new PitchProcessor(PitchProcessor.PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, pdh);
        dispatcher.addAudioProcessor(p);
        new Thread(dispatcher,"Audio Dispatcher").start();

        return v;
        // The code above is from http://0110.be/posts/TarsosDSP_on_Android_-_Audio_Processing_in_Java_on_Android.
    }

    /**
     * Gets the note that corresponds to the detected pitch
     * @param pitch
     * @return note in mNoteArray
     */
    private String getNote(float pitch) {
        double p = (double) pitch;              // Pitch in Hertz
        double e = (Math.log(p)-2.79)/0.058;
        // linearization of note frequencies; ln(note frequency) = 2.79 + 0.058*(note number)
        int i = Math.round((float) e);          // Closest index in mNoteArray

        return mNoteArray[i];
    }

    /**
     * Calculates the difference between the detected frequency and literature value frequency
     * @param pitch
     * @return color
     */
    private String getDifference(float pitch) {

        double c = 1.0/12.0*Math.log(2.0);
        // Standard difference between the natural log of the frequencies of consecutive notes
        double p = (double) pitch;              // Pitch in Hertz
        double e = (Math.log(p)-2.79)/c;
        // linearization of note frequencies; ln(note frequency) = 2.79 + 0.058*(note number)
        int ind = Math.round((float) e);       // Closest index in the mNoteArray
        double lp = 2.79 + (ind*c);            // Natural log of literature value frequency
        double diff = Math.log(pitch) - lp;
        // Difference between natural log of literature value and natural log of detected frequency

        if (diff > 0.02) {
            return rightRed;            // >0.02
        } else if (diff > 0.008) {
            return rightYellow;         // 0.008~0.02
        } else if (diff > -0.008) {
            return midGreen;            // -0.008~0.008
        } else if (diff > -0.02) {
            return leftYellow;          // -0.02~-0.008
        } else if (diff > -0.029) {
            return leftRed;             // -0.029~-0.02
        } else {
            return "";
        }
    }

    /**
     * Sets the state of pitch meter
     * @param color
     */
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

    /**
     * Resets pitch meter
     */
    private void resetColor() {
        mNegRed.setBackgroundColor(getResources().getColor(R.color.trans_red));
        mNegYellow.setBackgroundColor(getResources().getColor(R.color.trans_yellow));
        mNeuGreen.setBackgroundColor(getResources().getColor(R.color.trans_green));
        mPosYellow.setBackgroundColor(getResources().getColor(R.color.trans_yellow));
        mPosRed.setBackgroundColor(getResources().getColor(R.color.trans_red));
    }

}