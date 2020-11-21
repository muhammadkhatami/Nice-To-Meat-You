package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Note
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository.NoteRepository


class NoteViewModel(application: Application) : AndroidViewModel(application) {

    private var noteRepository = NoteRepository(application)

    fun insertNote(note: Note) {
        noteRepository.insert(note)
    }

    fun getNotesByRecipeId(id: Int):LiveData<List<Note>>? {
        return noteRepository.getNotesByRecipeId(id)
    }
}