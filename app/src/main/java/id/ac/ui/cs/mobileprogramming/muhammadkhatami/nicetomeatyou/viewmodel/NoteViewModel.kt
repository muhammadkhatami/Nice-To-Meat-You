package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Note
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository.NoteRepository


class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var noteRepository = NoteRepository(application)
    private var notes: LiveData<List<Note>>? = noteRepository.getNotes()

    fun insertNote(note: Note) {
        noteRepository.insert(note)
    }

    fun getNotes(): LiveData<List<Note>>? {
        return notes
    }

    fun deleteNote(note: Note) {
        noteRepository.delete(note)
    }

    fun updateNote(note: Note) {
        noteRepository.update(note)
    }

    fun getNotesByRecipeId(id: Int):LiveData<List<Note>>? {
        return noteRepository.getNotesByRecipeId(id)
    }
}