package com.example.android.pitchmaestro;

import java.util.UUID;

/**
 * Created by stephen on 11/16/15.
 */
public class Note {

    private UUID mId;
    private String mTitle;
    private String mPianoFile;
    private String mStringFile;
    private String mPipeFile;

    public Note(){
        mId = UUID.randomUUID();
    }

    public String getTitle(){
        return mTitle;
    }

    public void setTitle(String title){
        mTitle = title;
    }

    public UUID getId(){
        return mId;
    }

    public String getPipeFile() {
        return mPipeFile;
    }

    public void setPipeFile(String pipeFile) {
        mPipeFile = pipeFile;
    }

    public String getPianoFile() {
        return mPianoFile;
    }

    public void setPianoFile(String pianoFile) {
        mPianoFile = pianoFile;
    }

    public String getStringFile() {
        return mStringFile;
    }

    public void setStringFile(String stringFile) {
        mStringFile = stringFile;
    }
}
