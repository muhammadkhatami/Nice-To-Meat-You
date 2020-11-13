package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.UserDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.User

// Annotates class to be a Room Database with a table (entity) of the User class
@Database(entities = arrayOf(User::class), version = 1, exportSchema = false)
public abstract class UserRoomDatabase : RoomDatabase() {

    abstract fun userDao(): UserDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: UserRoomDatabase? = null

        fun getDatabase(context: Context): UserRoomDatabase {
            // if the INSTANCE is not null, then return it,
            // if it is, then create the database
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserRoomDatabase::class.java,
                    "users"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}