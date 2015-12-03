package com.example.android.pitchmaestro;

import java.util.UUID;

/**
 * Created by stephen on 11/16/15.
 */
public class Note {

    private UUID mId;
    private String mTitle;

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
}
