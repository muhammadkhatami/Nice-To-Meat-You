package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Note

@Dao
interface NoteDao {

    @Query("Select * from notes")
    fun getNotes(): LiveData<List<Note>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNote(note: Note)

    @Delete
    suspend fun deleteNote(note: Note)

    @Update
    suspend fun updateNote(note: Note)

    @Query("select * from notes where recipeId = :recipeId")
    fun getNotesByRecipeId(recipeId: Int): LiveData<List<Note>>
}