package com.example.licznik;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "room_notes")
public class Note {
    @PrimaryKey
    private long id;
    private String noteTitle;
    private String noteContent;

    public Note() {
    }

    public Note(String noteTitle, String noteContent) {
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public Note(long id, String noteTitle, String noteContent) {
        this.id = id;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNoteTitle() {
        return noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    public String toString() {
        return  id + "  " + noteTitle + ": "
                 + noteContent;
    }
}
