package com.example.licznik;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class NoteListAdapter extends ArrayAdapter<Note> {

    private Context context;
    private RoomDao roomDao;
    private List<Note> notes;

    public NoteListAdapter(Context context, RoomDao roomDao) {
        super(context, 0);
        this.context = context;
        this.roomDao = roomDao;
        this.notes = new ArrayList<>();
    }

    public void setNotes(List<Note> notes) {
        this.notes = notes;
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return notes.size();
    }

    @Override
    public Note getItem(int position) {
        return notes.get(position);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.list_item_note, parent, false);

            holder = new ViewHolder();
            holder.titleTextView = convertView.findViewById(R.id.text_title);
            holder.contentTextView = convertView.findViewById(R.id.text_content);
            holder.deleteButton = convertView.findViewById(R.id.button_delete);
            holder.editButton = convertView.findViewById(R.id.button_edit);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        Note note = getItem(position);
        holder.titleTextView.setText(note.getNoteTitle());
        holder.contentTextView.setText(note.getNoteContent());

        holder.deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNote(position);
                Toast.makeText(v.getContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        });

        holder.editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note selectedNote = getItem(position);

                Intent intent = new Intent(getContext(), EditNoteActivity.class);
                intent.putExtra("note_id", selectedNote.getId());
                intent.putExtra("note_title", selectedNote.getNoteTitle());
                intent.putExtra("note_content", selectedNote.getNoteContent());
                getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    private static class ViewHolder {
        TextView titleTextView;
        TextView contentTextView;
        Button deleteButton;
        Button editButton;
    }

    public void deleteNote(int position) {
        if (position >= 0 && position < notes.size()) {
            Note note = notes.get(position);
            notes.remove(position);
            notifyDataSetChanged();

            RoomNoteDatabase.databaseWriteExecutor.execute(() -> {
                roomDao.delete(note);
            });
        }
    }
}