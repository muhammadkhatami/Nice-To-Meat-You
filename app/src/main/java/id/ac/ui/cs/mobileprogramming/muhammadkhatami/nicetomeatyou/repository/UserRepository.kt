package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.UserDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.room.UserRoomDatabase
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.User
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UserRepository(application: Application) {

    private val userDao: UserDao?
    private var users: LiveData<List<User>>? = null

    init {
        val db = UserRoomDatabase.getDatabase(application.applicationContext)
        userDao = db?.userDao()
        users = userDao?.getUsers()
    }

    fun getUsers(): LiveData<List<User>>? {
        return users
    }

    fun insert(user: User) = runBlocking {
        this.launch(Dispatchers.IO) {
            userDao?.insertUser(user)
        }
    }

    fun delete(user: User) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                userDao?.deleteUser(user)
            }
        }
    }

    fun update(user: User) = runBlocking {
        this.launch(Dispatchers.IO) {
            userDao?.updateUser(user)
        }
    }

}