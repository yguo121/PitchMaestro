package com.example.android.pitchmaestro;

import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.pitchmaestro.radialmenu.DividerItemDecoration;

import java.io.IOException;
import java.util.List;

/**
 * Created by stephen on 11/16/15.
 */
public class NoteListFragmentPipe extends Fragment {


    private RecyclerView  mNoteRecyclerView;
    private NoteAdapter mAdapter;

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

    private void updateUI(){
        NoteLab noteLab = NoteLab.get(getActivity());
        List<Note> notes = noteLab.getNotes();

        mAdapter = new NoteAdapter(notes);
        mNoteRecyclerView.setAdapter(mAdapter);
    }

    private class NoteHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private TextView mTitleTextView;
        private Note mNote;
        public NoteHolder(View itemView){
            super(itemView);
            itemView.setOnClickListener(this);
            mTitleTextView = (TextView) itemView.findViewById(R.id.list_item_note_title_text_view);
        }

        public void bindNote(Note note){
            mNote = note;
            mTitleTextView.setText(mNote.getTitle());
        }

        @Override
        public void onClick(View v){

            mTitleTextView.setBackgroundColor(0xFFC0C0C0);

            final MediaPlayer mp = new MediaPlayer();

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
                }
            });
        }


    }

    private class NoteAdapter extends RecyclerView.Adapter<NoteHolder>{
        private List<Note> mNotes;

        public NoteAdapter(List<Note>notes){
            mNotes = notes;
        }

        @Override
        public NoteHolder onCreateViewHolder(ViewGroup parent, int viewType){
            LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
            View view = layoutInflater.inflate(R.layout.list_item_note, parent, false);
            return new NoteHolder(view);
        }

        @Override
        public void onBindViewHolder(NoteHolder holder, int position){
            Note note = mNotes.get(position);
            holder.bindNote(note);
        }

        @Override
        public int getItemCount(){
            return mNotes.size();
        }

    }

    public static NoteListFragmentPiano newInstance() {
        NoteListFragmentPiano f = new NoteListFragmentPiano();
        return f;
    }
}

