package com.iroshana.room;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {
    private NoteDAO noteDAO;

    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDAO = database.noteDao();
        allNotes = noteDAO.getAllNotes();
    }

    public void insert(Note note) {
        new InsertNoteAsyncTask(noteDAO).execute(note);

    }

    public void update(Note note) {
        new UpdateNoteAsyncTask(noteDAO).execute(note);
    }

    public void delete(Note note) {
        new DeleteNoteAsyncTask(noteDAO).execute(note);
    }

    public void deleteAllNotes() {
        new DeleteAllNoteAsyncTask(noteDAO).execute();
    }

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }


    public static class InsertNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;

        private InsertNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    public static class UpdateNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;

        private UpdateNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    public static class DeleteNoteAsyncTask extends AsyncTask<Note, Void, Void> {
        private NoteDAO noteDao;

        private DeleteNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    public static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        private NoteDAO noteDao;

        private DeleteAllNoteAsyncTask(NoteDAO noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
