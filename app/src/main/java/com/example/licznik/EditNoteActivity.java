package com.example.licznik;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class EditNoteActivity extends AppCompatActivity {

    private EditText editTextTitle;
    private EditText editTextContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editnote);

        Intent intent = getIntent();

        String noteId = intent.getStringExtra("note_id");
        String noteTitle = intent.getStringExtra("note_title");
        String noteContent = intent.getStringExtra("note_content");

        editTextTitle = findViewById(R.id.editNoteTitle);
        editTextContent = findViewById(R.id.editNoteContent);
        editTextTitle.setText(noteTitle);
        editTextContent.setText(noteContent);

        RoomNoteDatabase db = RoomNoteDatabase.getInstance(this);
        RoomDao roomDao = db.roomDao();
    }

    public void onEditClick(View view) {

        String title = editTextTitle.getText().toString();
        String content = editTextContent.getText().toString();

        Long noteId = getIntent().getLongExtra("note_id", 0);

        RoomNoteDatabase db = RoomNoteDatabase.getInstance(this);
        RoomDao roomDao = db.roomDao();

        RoomNoteDatabase.databaseWriteExecutor.execute(() -> {
            roomDao.updateNote(noteId, title, content);
            Log.d("DB", "Updated in DB " + noteId + " " + title + " " + content);

            runOnUiThread(() -> {
                Toast.makeText(this, "Note updated", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }

    public void onBackPressed(View view) {
        finish();
    }

}
