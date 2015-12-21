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
public class NotePianoLab {

    private static NotePianoLab sNoteLab;        // the note.
    private List<Note> mNotes;              // the list of all the notes.


    /**
     * The constructor of the notes.
     * @return Notelab - the note lab.
     */
    public static NotePianoLab get(Context context){
        if (sNoteLab == null){
            sNoteLab = new NotePianoLab();
        }
        return sNoteLab;
    }

    /**
     * The constructor of the notes which sets the title and the audio file accordingly.
     */
    private NotePianoLab(){
        mNotes = new ArrayList<>();

        for (int i = 0; i < 88; i++) {
            Note note = new Note();

            String[] octaves = {"\u2080", "\u2081", "\u2082", "\u2083",
                    "\u2084", "\u2085", "\u2086", "\u2087", "\u2088"};

            int nOctave = (i < 2) ? 0 : (i + 9)/12; // the octave the current note is in
            int nNote = (i + 9) % 12;               // the note name of the current note
            int indexPiano = i + 1;                 // index of the piano files

            // Set the file name.
            note.setPianoFile("piano (" + indexPiano + ").wav");

            // Set the title accordingly.
            switch(nNote) {
                case 0:
                    note.setTitle("C" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 1:
                    note.setTitle("C\u266F" + octaves[nOctave] + "/D\u266D" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 2:
                    note.setTitle("D" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 3:
                    note.setTitle("D\u266F" + octaves[nOctave] + "/E\u266D" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 4:
                    note.setTitle("E" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 5:
                    note.setTitle("F" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 6:
                    note.setTitle("F\u266F" + octaves[nOctave] + "/G\u266D" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 7:
                    note.setTitle("G" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 8:
                    note.setTitle("G\u266F" + octaves[nOctave] + "/A\u266D" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 9:
                    note.setTitle("A" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 10:
                    note.setTitle("A\u266F" + octaves[nOctave] + "/B\u266D" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
                case 11:
                    note.setTitle("B" + octaves[nOctave]);
                    mNotes.add(note);
                    break;
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
