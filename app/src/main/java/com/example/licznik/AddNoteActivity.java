package com.example.licznik;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AddNoteActivity extends AppCompatActivity {
    private EditText addTextTitle;
    private EditText addTextContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addnote);


        addTextTitle = findViewById(R.id.addNoteTitle);
        addTextContent = findViewById(R.id.addNoteContent);
    }

    public void onApproveClick(View view) {
        String title = addTextTitle.getText().toString();
        String content = addTextContent.getText().toString();

        if (title.isEmpty()) {
            title = "Notatka";
        }

        Note newNote = new Note();
        newNote.setNoteTitle(title);
        newNote.setNoteContent(content);

        RoomNoteDatabase db = RoomNoteDatabase.getInstance(this);
        RoomDao roomDao = db.roomDao();

        RoomNoteDatabase.databaseWriteExecutor.execute(() -> {
            roomDao.insertAll(newNote);

            Log.d("DB", "Inserted to DB " + newNote.getId() + " " + newNote.getNoteTitle() +
                    " " + newNote.getNoteContent());
            runOnUiThread(() -> {
                Toast.makeText(this, "Note added", Toast.LENGTH_SHORT).show();
                finish();
            });
        });
    }

    public void onBackPressed(View view) {
        finish();
    }
}
