package com.example.android.pitchmaestro;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.pitchmaestro.radialmenu.DividerItemDecoration;

import java.io.IOException;
import java.util.List;

/**
 * This class contains the pipenotes list.
 *
 * @author Yinghuan Wang (yinghuanwang521@gmail.com )
 * @author Yaoqi Guo (yaoqi.guo@trincoll.edu)
 * @author Peter Jung (peter.jung@trincoll.edu)
 */
public class NoteListFragmentPipe extends Fragment {


    private RecyclerView  mNoteRecyclerView;        // the recyclerview of the list.
    private NoteAdapter mAdapter;                   // the adapter of the recycler view

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_note_list_pipe, container, false);

        mNoteRecyclerView = (RecyclerView) view.findViewById(R.id.note_list_pipe);
        mNoteRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mNoteRecyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        mNoteRecyclerView.setSelected(true);

        updateUI();
        return view;
    }

    /**
     * updates the UI accordingly.
     */
    private void updateUI(){
        NotePipeLab noteLab = NotePipeLab.get(getActivity());
        List<Note> notes = noteLab.getNotes();

        mAdapter = new NoteAdapter(notes);
        mNoteRecyclerView.setAdapter(mAdapter);
    }

    /**
     * This class is the note holder class which implements all the functions of the note holder .
     */
    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private Note mNote;

        /**
         * The constructor of the note holder.
         * @param  itemView - the view of the note holder.
         */
        public NoteHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_note_title_text_view);
        }

        /**
         * bind the note in the note holder with the note in the mNote list .
         * @return HomePageFragment - the home page fragment.
         */
        public void bindNote(Note note){
            mNote = note;
            mTitleTextView.setText(mNote.getTitle());
        }

        /**
         * set the click listener .
         * @param v - the view.
         */
        @Override
        public void onClick(View v){

            mTitleTextView.setBackgroundColor(0xFFD1EEEE);

            final MediaPlayer mp = new MediaPlayer();       //start a media player

            if (mp.isPlaying())
            {
                mp.stop();
            }

            try {
                mp.reset();

                AssetFileDescriptor afd;
                afd = getContext().getAssets().openFd(mNote.getPipeFile());
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());
                mp.prepare();
                mp.start();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            mp.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mp) {
                    mTitleTextView.setBackgroundColor(0x00FFFFFF);
                    mp.release();
                    //release the media player
                }
            });
        }
    }

    /**
     * The adapter class. Creates an adapter for the recycler view.
     */
    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>{

        private List<Note> mNotes;          //the list of notes.

        /**
         * The constructor of the adapter.
         * @return note adapter.
         */
        public NoteAdapter(List<Note>notes){
            mNotes = notes;
        }

        /**
         * Create the note holder.
         * @param parent - the parent.
         * @param viewType - the view type.
         * @return returns a note holder.
         */
        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);
            return new NoteHolder(view);
        }

        /**
         * Bind the note with the note holder.
         * @param holder - the note holder.
         * @param position - the position of the current holder.
         */
        @Override
        public void onBindViewHolder(NoteHolder holder, int position){
            Note note = mNotes.get(position);
            holder.bindNote(note);
        }

        /**
         * Count the size of the note list.
         * @return the size of the list
         */
        @Override
        public int getItemCount(){
            return mNotes.size();
        }

    }

}

