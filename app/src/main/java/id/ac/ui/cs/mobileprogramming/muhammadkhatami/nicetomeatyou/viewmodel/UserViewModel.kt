package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository.UserRepository
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.User

class UserViewModel(application: Application) : AndroidViewModel(application) {

    private var userRepository = UserRepository(application)
    private var users: LiveData<List<User>>? = userRepository.getUsers()

    fun insertUser(user: User) {
        userRepository.insert(user)
    }

    fun getUsers(): LiveData<List<User>>? {
        return users
    }

    fun deleteUser(user: User) {
        userRepository.delete(user)
    }

    fun updateUser(user: User) {
        userRepository.update(user)
    }
}