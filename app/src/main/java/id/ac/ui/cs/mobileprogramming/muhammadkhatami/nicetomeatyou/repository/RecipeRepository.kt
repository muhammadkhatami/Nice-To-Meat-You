package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository

import android.app.Application
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.dao.RecipeDao
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.room.RecipeRoomDatabase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class RecipeRepository(application: Application) {

    private val recipeDao: RecipeDao?
    private var recipes: LiveData<List<Recipe>>? = null

    init {
        val db = RecipeRoomDatabase.getDatabase(application.applicationContext)
        recipeDao = db?.recipeDao()
        recipes = recipeDao?.getRecipes()
    }

    fun getRecipes(): LiveData<List<Recipe>>? {
        return recipes
    }

    fun insert(recipe: Recipe) = runBlocking {
        this.launch(Dispatchers.IO) {
            recipeDao?.insertRecipe(recipe)
        }
    }

    fun delete(recipe: Recipe) {
        runBlocking {
            this.launch(Dispatchers.IO) {
                recipeDao?.deleteRecipe(recipe)
            }
        }
    }

    fun update(recipe: Recipe) = runBlocking {
        this.launch(Dispatchers.IO) {
            recipeDao?.updateRecipe(recipe)
        }
    }

}