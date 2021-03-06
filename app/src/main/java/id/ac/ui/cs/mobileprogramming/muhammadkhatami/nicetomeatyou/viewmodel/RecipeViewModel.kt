package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository.CategoryRepository
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.repository.RecipeRepository

class RecipeViewModel(application: Application) : AndroidViewModel(application) {

    private var recipeRepository = RecipeRepository(application)
    private var recipes: LiveData<List<Recipe>>? = recipeRepository.getRecipes()

    private var categoryRepository = CategoryRepository(application)
    private var categories: LiveData<List<Category>>? = categoryRepository.getCategories()

    fun insertRecipe(recipe: Recipe) {
        recipeRepository.insert(recipe)
    }

    fun getRecipes(): LiveData<List<Recipe>>? {
        return recipes
    }

    fun deleteRecipe(recipe: Recipe) {
        recipeRepository.delete(recipe)
    }

    fun updateRecipe(recipe: Recipe) {
        recipeRepository.update(recipe)
    }

    fun allCategories(): LiveData<List<Category>>? {
        return categories
    }
}