package com.example.android.pitchmaestro;


import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * This is the class of the Note.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
 */
public class NoteLab {

    private static NoteLab sNoteLab;        // the note.

    private List<Note> mNotes;              // the list of all the notes.


    /**
     * The constructor of the notes.
     * @return Notelab - the note lab.
     */
    public static NoteLab get(Context context){
        if (sNoteLab == null){
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    /**
     * The constructor of the notes which sets the title and the audio file accordingly.
     * @param context
     */
    private NoteLab(Context context){
        mNotes = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 12; j++) {
                Note note = new Note();

                int indexPipe = i*12 + j + 1;       // index of the pipe
                int indexPiano = indexPipe - 9;     // index of the piano
                int indexString = indexPipe;        // index of the string

                // set the file name.
                note.setPipeFile("pipe (" + indexPipe + ").wav");
                note.setPianoFile("piano (" + indexPiano + ").wav");
                note.setStringFile("string (" + indexString + ").wav");

                // set the title accordingly
                switch(j) {
                    case 0:
                        note.setTitle("C" + i);
                        mNotes.add(note);
                        break;
                    case 1:
                        note.setTitle("C\u266F" + i + "/D\u266D" + i);
                        mNotes.add(note);
                        break;
                    case 2:
                        note.setTitle("D" + i);
                        mNotes.add(note);
                        break;
                    case 3:
                        note.setTitle("D\u266F" + i + "/E\u266D" + i);
                        mNotes.add(note);
                        break;
                    case 4:
                        note.setTitle("E" + i);
                        mNotes.add(note);
                        break;
                    case 5:
                        note.setTitle("F" + i);
                        mNotes.add(note);
                        break;
                    case 6:
                        note.setTitle("F\u266F" + i + "/G\u266D" + i);
                        mNotes.add(note);
                        break;
                    case 7:
                        note.setTitle("G" + i);
                        mNotes.add(note);
                        break;
                    case 8:
                        note.setTitle("G\u266F" + i + "/A\u266D" + i);
                        mNotes.add(note);
                        break;
                    case 9:
                        note.setTitle("A" + i);
                        mNotes.add(note);
                        break;
                    case 10:
                        note.setTitle("A\u266F" + i + "/B\u266D" + i);
                        mNotes.add(note);
                        break;
                    case 11:
                        note.setTitle("B" + i);
                        mNotes.add(note);
                        break;
                }
            }
        }
    }

    /**
     * The getter of the note list.
     * @return mNotes - the list of notes.
     */
    public List<Note> getNotes(){
        return mNotes;
    }

}
