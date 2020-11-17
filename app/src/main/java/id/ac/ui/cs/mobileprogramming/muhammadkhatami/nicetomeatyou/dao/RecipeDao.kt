package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe

@Dao
interface RecipeDao {

    @Query("Select * from recipes")
    fun getRecipes(): LiveData<List<Recipe>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(recipe: Recipe)

    @Delete
    suspend fun deleteRecipe(recipe: Recipe)

    @Update
    suspend fun updateRecipe(recipe: Recipe)
}