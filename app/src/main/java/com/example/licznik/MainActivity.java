package com.example.licznik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;
    private RoomDao roomDao;

    private NoteListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = findViewById(R.id.list);

        RoomNoteDatabase db = RoomNoteDatabase.getInstance(this);
        roomDao = db.roomDao();

        Note truskawki = new Note();
        truskawki.setNoteTitle("Zakupy");
        truskawki.setNoteContent("Kupić Truskawki");

        Note maliny= new Note();
        maliny.setNoteTitle("Zakupy");
        maliny.setNoteContent("Kupić Maliny");

        Note ogorki = new Note();
        ogorki.setNoteTitle("Zakupy");
        ogorki.setNoteContent("Kupić ogorki");

        adapter = new NoteListAdapter(this, roomDao);
        list.setAdapter(adapter);


//        RoomNoteDatabase.databaseWriteExecutor.execute(() -> {
//            roomDao.deleteAll();
//            roomDao.insertAll(truskawki, maliny, ogorki);
//        });

        roomDao.getAll().observe(this, new Observer<List<Note>>() {
            @Override
            public void onChanged(List<Note> notes) {
                adapter.setNotes(notes);
            }
        });
    }

    public void addNoteActivity(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }
}