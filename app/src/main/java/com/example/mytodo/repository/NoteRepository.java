package com.example.mytodo.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.mytodo.Entity.Note;
import com.example.mytodo.dao.NoteDao;
import com.example.mytodo.database.NoteDatabase;

import java.util.List;

public class NoteRepository {
    private NoteDao noteDao;
    private LiveData<List<Note>> allNotes;

    public NoteRepository(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

//    Our abstraction layer for Room
    public void insert(Note note){
        new InsertNoteAsync(noteDao).execute(note);
    }

    public void update(Note note){
        new UpdateNoteAsync(noteDao).execute(note);
    }

    public void delete(Note note){
        new DeleteNoteAsync(noteDao).execute(note);
    }

    public void deleteAll(){
        new DeleteAllNotesAsync(noteDao).execute();
    }
//

    public LiveData<List<Note>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsync extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private InsertNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsync extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private UpdateNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsync extends AsyncTask<Note,Void,Void>{
        private NoteDao noteDao;

        private DeleteNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Note... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNotesAsync extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        private DeleteAllNotesAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.deleteAll();
            return null;
        }
    }




}
