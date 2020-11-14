package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.User

@Dao
interface UserDao {

    @Query("Select * from users")
    fun getUsers(): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: User)

    @Delete
    suspend fun deleteUser(user: User)

    @Update
    suspend fun updateUser(user: User)
}