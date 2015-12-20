package com.example.android.pitchmaestro;


import android.content.Context;

import java.util.ArrayList;
import java.util.List;

/**
 * This is the class of the Note.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
 */
public class NotePipeLab {

    private static NotePipeLab sNoteLab;        // the note.
    private List<Note> mNotes;              // the list of all the notes.


    /**
     * The constructor of the notes.
     * @return Notelab - the note lab.
     */
    public static NotePipeLab get(Context context){
        if (sNoteLab == null){
            sNoteLab = new NotePipeLab();
        }
        return sNoteLab;
    }

    /**
     * The constructor of the notes which sets the title and the audio file accordingly.
     */
    private NotePipeLab(){
        mNotes = new ArrayList<>();

        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 12; j++) {
                Note note = new Note();

                int indexPipe = i*12 + j + 1;                       // index of the pipe
                String[] octaves = {"\u2080", "\u2081", "\u2082", "\u2083",
                        "\u2084", "\u2085", "\u2086", "\u2087", "\u2088"};

                note.setPipeFile("pipe (" + indexPipe + ").wav");   // set the file name.

                // set the title accordingly
                switch(j) {
                    case 0:
                        note.setTitle("C" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 1:
                        note.setTitle("C\u266F" + octaves[i] + "/D\u266D" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 2:
                        note.setTitle("D" + i);
                        mNotes.add(note);
                        break;
                    case 3:
                        note.setTitle("D\u266F" + octaves[i] + "/E\u266D" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 4:
                        note.setTitle("E" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 5:
                        note.setTitle("F" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 6:
                        note.setTitle("F\u266F" + octaves[i] + "/G\u266D" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 7:
                        note.setTitle("G" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 8:
                        note.setTitle("G\u266F" + octaves[i] + "/A\u266D" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 9:
                        note.setTitle("A" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 10:
                        note.setTitle("A\u266F" + octaves[i] + "/B\u266D" + octaves[i]);
                        mNotes.add(note);
                        break;
                    case 11:
                        note.setTitle("B" + octaves[i]);
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
