package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.NoteDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.CateogryDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Note
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe

// Annotates class to be a Room Database with a table (entity) of the Recipe class
@Database(entities = arrayOf(Recipe::class, Category::class, Note::class), version = 11, exportSchema = false)
public abstract class RecipeRoomDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun categoryDao(): CateogryDao
    abstract fun noteDao(): NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: RecipeRoomDatabase? = null

        fun getDatabase(context: Context): RecipeRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RecipeRoomDatabase::class.java,
                    "nicetomeatyou"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                // return instance
                instance

            }
        }
    }
}