package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.NoteDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.CateogryDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Note
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

// Annotates class to be a Room Database with a table (entity) of the Recipe class
@Database(entities = arrayOf(Recipe::class, Category::class, Note::class), version = 7, exportSchema = false)
public abstract class NiceToMeatYouRoomDatabase : RoomDatabase() {

    abstract fun recipeDao(): RecipeDao
    abstract fun categoryDao(): CateogryDao
    abstract fun noteDao(): NoteDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: NiceToMeatYouRoomDatabase? = null

            fun getDatabase(context: Context,  scope: CoroutineScope): NiceToMeatYouRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NiceToMeatYouRoomDatabase::class.java,
                    "nicetomeatyou"
                ).fallbackToDestructiveMigration()
                    .addCallback(NiceToMeatYouRoomDatabaseCallback(scope))
                    .build()
                INSTANCE = instance
                // return instance
                instance

            }
        }
    }

    private class NiceToMeatYouRoomDatabaseCallback(
        private val scope: CoroutineScope
    ) : RoomDatabase.Callback() {

        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                scope.launch {
                    populateDatabase(database.categoryDao())
                }
            }
        }

        suspend fun populateDatabase(cateogryDao: CateogryDao) {
            var category = Category(1, "Winter Food")
            cateogryDao.insertCategory(category)
            category = Category(2, "Summer Food")
            cateogryDao.insertCategory(category)
            category = Category(3, "Anime Food")
            cateogryDao.insertCategory(category)
        }
    }
}