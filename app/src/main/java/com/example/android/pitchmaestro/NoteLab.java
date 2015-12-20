package com.example.android.pitchmaestro;


import android.content.ContentValues;
import android.content.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * Created by stephen on 11/15/15.
 */
public class NoteLab {
    private static NoteLab sNoteLab;

    //private String[] mNoteList = {"C","C#"}

    private List<Note> mNotes;

    public static NoteLab get(Context context){
        if (sNoteLab == null){
            sNoteLab = new NoteLab(context);
        }
        return sNoteLab;
    }

    private NoteLab(Context context){
        mNotes = new ArrayList<>();
//        for (int i = 0; i < 96; i++){
//            Note note = new Note();
//            note.setTitle("Note #" + i);
//            mNotes.add(note);
//        }
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 12; j++) {
                Note note = new Note();

                int indexPipe = i*12 + j + 1;
                int indexPiano = indexPipe - 9;
                int indexString = indexPipe;

                note.setPipeFile("pipe (" + indexPipe + ").wav");
                note.setPianoFile("piano (" + indexPiano + ").wav");
                note.setStringFile("string (" + indexString + ").wav");

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

    public List<Note> getNotes(){
        return mNotes;
    }
//
//    public Note getNote(UUID id){
//        for (Note note : mNotes){
//            if (note.getId().equals(id)){
//                return note;
//            }
//        }
//        return null;
//    }
}
