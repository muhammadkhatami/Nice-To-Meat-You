package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.RecipeViewModel
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.UserViewModel

class CreateRecipeActivity : AppCompatActivity() {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("onCreateRecipe", "------------------onCreateRecipe-------------------------")

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        val profile = findViewById(R.id.buttonCreateRecipe) as Button
        profile.setOnClickListener{
            createRecipe()
        }

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

    }

    fun createRecipe() {
        val recipeTitle = findViewById(R.id.recipeTitle) as EditText
        val recipeTime = findViewById(R.id.recipeTime) as EditText

        Log.d("pushRecipe", "------------------pushRecipe-------------------------")

        recipeViewModel.insertRecipe(
            Recipe(
                recipe_title = recipeTitle.text.toString(),
                time = recipeTime.text.toString().toInt(),
                photo = "ini photo dummy",
                userId = 1
            )
        )
        onBackPressed()
    }

    private fun showAlertMenu(recipe: Recipe) {

    }
}