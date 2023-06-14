package com.example.licznik;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.RoomDatabase;

public class AddNoteActivity extends AppCompatActivity {
    private EditText editTextTitle;
    private EditText editTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.addnote);


        editTextTitle = findViewById(R.id.addNoteTitle);
        editTextContent = findViewById(R.id.editTextContent);
    }

    public void onApproveClick(View view) {
        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();

        Note newNote = new Note(title, content);

        RoomNoteDatabase db = RoomNoteDatabase.getDatabase(this);
        RoomDao roomDao = db.roomDao();

        roomDao.insertAll(newNote);
        // Handle the input values as desired (e.g., save to a database, display a toast message, etc.)
        onBackPressed();
    }

    public void onBackPressed(View view) {
        finish(); // Close the current activity and go back to the previous activity (MainActivity)
    }
}
