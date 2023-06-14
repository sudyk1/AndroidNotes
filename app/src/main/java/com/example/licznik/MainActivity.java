package com.example.licznik;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;


import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ListView list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        list = (ListView) findViewById(R.id.list);

        RoomNoteDatabase db = RoomNoteDatabase.getDatabase(this);
        RoomDao roomDao = db.roomDao();

//        RoomNoteDatabase.databaseWriteExecutor.execute(() -> {
//            roomDao.deleteAll();
//            roomDao.insertAll(new Note(1, "Zakupy", "Kupic truskawki"),
//                    new Note(2, "Zakupy", "Kupic borowki"),
//                    new Note(3, "Zakupy", "Kupic sok"));
//        });

        LiveData<List<Note>> notes = roomDao.getAll();
        notes.observe(this, words -> {
            words.forEach(w -> {
                Log.d("DB", w.toString());
            });
            ArrayAdapter<Note> adapter = new ArrayAdapter<Note>(this, android.R.layout.simple_list_item_1, words);
            list.setAdapter(adapter);
        });
    }

    public void addNoteActivity(View view) {
        Intent intent = new Intent(this, AddNoteActivity.class);
        startActivity(intent);
    }

}