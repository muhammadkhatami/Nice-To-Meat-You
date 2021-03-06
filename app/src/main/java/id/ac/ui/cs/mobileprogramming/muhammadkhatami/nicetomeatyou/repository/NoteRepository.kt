package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.NoteDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Note
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.room.NiceToMeatYouRoomDatabase
import kotlinx.coroutines.*


class NoteRepository(application: Application) {

    private val noteDao: NoteDao?
    private var notes: LiveData<List<Note>>? = null
    private var notesByRecipeId: LiveData<List<Note>>? = null
    val applicationScope = CoroutineScope(SupervisorJob())

    init {
        val db = NiceToMeatYouRoomDatabase.getDatabase(application.applicationContext, applicationScope)
        noteDao = db?.noteDao()
        notes = noteDao?.getNotes()
    }

    fun getNotes(): LiveData<List<Note>>? {
        return notes
    }

    fun insert(note: Note) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDao?.insertNote(note)
        }
    }

    fun delete(note: Note) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                noteDao?.deleteNote(note)
            }
        }
    }

    fun update(note: Note) = runBlocking {
        this.launch(Dispatchers.IO) {
            noteDao?.updateNote(note)
        }
    }

    fun getNotesByRecipeId(id: Int): LiveData<List<Note>>? {
        notesByRecipeId = noteDao?.getNotesByRecipeId(id)
        return notesByRecipeId
    }

}