package com.example.android.pitchmaestro;


        import be.tarsos.dsp.AudioDispatcher;
        import be.tarsos.dsp.AudioEvent;
        import be.tarsos.dsp.io.android.AudioDispatcherFactory;
        import be.tarsos.dsp.pitch.PitchDetectionHandler;
        import be.tarsos.dsp.pitch.PitchDetectionResult;
        import be.tarsos.dsp.pitch.PitchProcessor;
        import be.tarsos.dsp.pitch.PitchProcessor.PitchEstimationAlgorithm;
        import android.os.Bundle;
        import android.support.v4.app.Fragment;
        import android.support.v7.app.ActionBarActivity;
        import android.view.LayoutInflater;
        import android.view.Menu;
        import android.view.MenuItem;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.Button;
        import android.widget.TextView;

public class PitchDetectorActivity extends ActionBarActivity {

    private Button mNegRed;
    private Button mNegYellow;
    private Button mNeuGreen;
    private Button mPosYellow;
    private Button mPosRed;
    private TextView mNoteDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tarsos_dsp);

        mNegRed = (Button) findViewById(R.id.negative_red);
        //mNegRed.setBackgroundColor(getResources().getColor(R.color.trans_yellow));

        mNegYellow = (Button) findViewById(R.id.negative_yellow);
//        mNegYellow.setBackgroundColor(getResources().getColor(R.color.trans_yellow));

        mNeuGreen = (Button) findViewById(R.id.neutral_green);
        //mNeuGreen.setBackgroundColor(getResources().getColor(R.color.trans_green));

        mPosYellow = (Button) findViewById(R.id.positive_yellow);
        //mPosYellow.setBackgroundColor(getResources().getColor(R.color.trans_yellow));

        mPosRed = (Button) findViewById(R.id.positive_red);
        //mPosRed.setBackgroundColor(getResources().getColor(R.color.trans_red));
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new PlaceholderFragment()).commit();
        }
        AudioDispatcher dispatcher = AudioDispatcherFactory.fromDefaultMicrophone(22050,1024,0);


        dispatcher.addAudioProcessor(new PitchProcessor(PitchEstimationAlgorithm.FFT_YIN, 22050, 1024, new PitchDetectionHandler() {

            @Override
            public void handlePitch(PitchDetectionResult pitchDetectionResult,
                                    AudioEvent audioEvent) {
                final float pitchInHz = pitchDetectionResult.getPitch();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        mNoteDisplay = (TextView) findViewById(R.id.note_display);
                        mNoteDisplay.setText("" + pitchInHz);
//                        if (pitchInHz < 500) {
//                            text.setText("A " + pitchInHz);
//                        }
//
//                        if ((pitchInHz > 500) && (pitchInHz < 1000)) {
//                            text.setText("B" + pitchInHz);
//                        }
//
//                        if ((pitchInHz > 1000) && (pitchInHz < 1500)) {
//                            text.setText("C" + pitchInHz);
//                        }
//
//                        if ((pitchInHz > 1500) && (pitchInHz < 2000)) {
//                            text.setText("D" + pitchInHz);
//                        }
//
//                        if (pitchInHz > 2000) {
//                            text.setText("E" + pitchInHz);
//                        }
                    }
                });

            }
        }));
        new Thread(dispatcher,"Audio Dispatcher").start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.tarsos_ds, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_pitch_detector, container, false);
            return rootView;
        }


    }
    @Override
    public void onStop(){
        super.onStop();
        mNoteDisplay.setText("");
    }
}