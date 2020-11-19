package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category

@Dao
interface CateogryDao {

    @Query("Select * from categories")
    fun getCategories(): LiveData<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertCategory(category: Category)

    @Delete
    suspend fun deleteCategory(category: Category)

    @Update
    suspend fun updateCategory(category: Category)
}