package com.example.licznik;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface RoomDao {

    @Insert
    void insertAll(Note... notes);

    @Delete
    void delete(Note note);

    @Query("SELECT * FROM room_notes")
    LiveData<List<Note>> getAll();

    @Query("DELETE FROM room_notes")
    void deleteAll();

}
