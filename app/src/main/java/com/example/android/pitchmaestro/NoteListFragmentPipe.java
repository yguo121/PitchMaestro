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
            // Highlight the current TextView when clicked
            // by setting the background color to transparent.
            mTitleTextView.setBackgroundColor(0xFFD1EEEE);

            // Initialize a MediaPlayer object.
            final MediaPlayer mp = new MediaPlayer();

            try {
                // Reset the MediaPlayer.
                mp.reset();

                // Use an AssetFileDescriptor object to locate the audio file to be played.
                AssetFileDescriptor afd;
                afd = getContext().getAssets().openFd(mNote.getPipeFile());

                // Link the audio file to be played with the MediaPlayer.
                mp.setDataSource(afd.getFileDescriptor(), afd.getStartOffset(), afd.getLength());

                // Prepare and start playing the linked audio file.
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
                    // Stop highlighting the current TextView after the audio finishes playing
                    // by setting the background color to transparent.
                    mTitleTextView.setBackgroundColor(0x00FFFFFF);

                    // Release the MediaPlayer to end its lifecycle.
                    mp.release();

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

