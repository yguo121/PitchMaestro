package com.example.android.pitchmaestro;

import java.util.UUID;

/**
 * This is the class of the Note.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 */
public class Note {

    private UUID mId;               // the id of the note.
    private String mTitle;          // the title of the note.
    private String mPianoFile;      // the piano file name.
    private String mStringFile;     // the string file name.
    private String mPipeFile;       // the pipe file name.

    /**
     * Constructor of the Note class.
     */
    public Note(){
        mId = UUID.randomUUID();
    }

    /**
     * Getter of the title .
     * @return mtitle - the title.
     */
    public String getTitle(){
        return mTitle;
    }

    /**
     * Setter of the title.
     * @param title - the name of the title.
     */
    public void setTitle(String title){
        mTitle = title;
    }

    /**
     * Getter of the Id
     * @return mId - the Id.
     */
    public UUID getId(){
        return mId;
    }

    /**
     * Getter of the pipe file.
     * @return mPipeFile - the pipe file name.
     */
    public String getPipeFile() {
        return mPipeFile;
    }

    /**
     * Setter of the title.
     * @param pipeFile -  the name of the pipe file.
     */
    public void setPipeFile(String pipeFile) {
        mPipeFile = pipeFile;
    }

    /**
     * Getter of the piano file.
     * @return mPianoFile - the piano file name.
     */
    public String getPianoFile() {
        return mPianoFile;
    }

    /**
     * Setter of the title.
     * @param pianoFile - the name of the piano file.
     */
    public void setPianoFile(String pianoFile) {
        mPianoFile = pianoFile;
    }

    /**
     * Getter of the String file.
     * @return mStringFile - the string file name.
     */
    public String getStringFile() {
        return mStringFile;
    }

    /**
     * Setter of the title.
     * @param stringFile - the name of the string file.
     */
    public void setStringFile(String stringFile) {
        mStringFile = stringFile;
    }
}