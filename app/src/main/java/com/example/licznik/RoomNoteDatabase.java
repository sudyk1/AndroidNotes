package com.example.licznik;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@androidx.room.Database(entities = {Note.class}, version = 1)
public abstract class RoomNoteDatabase extends RoomDatabase {

        public abstract RoomDao roomDao();
        private static volatile RoomNoteDatabase INSTANCE;
        private static final int NUMBER_OF_THREADS = 4;
        static final ExecutorService databaseWriteExecutor =
                Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        static RoomNoteDatabase getDatabase(final Context context) {
            if (INSTANCE == null) {
                synchronized (RoomNoteDatabase.class) {
                    if (INSTANCE == null) {
                        INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                                        RoomNoteDatabase.class, "kontakts")
                                .allowMainThreadQueries().build();
                    }
                }
            }
            return INSTANCE;
        }
    }
