package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity() {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        Log.d("RecipeActivityCreate", "------------------RecipeActivityCreate-------------------------")
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        recipeRV.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(this) { recipe, i ->
            showAlertMenu(recipe)
        }
        recipeRV.adapter = recipeAdapter

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        recipeViewModel.getRecipes()?.observe(this, Observer {
            recipeAdapter.setRecipes(it)
        })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.recipe_app_bar, menu)
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addRecipe -> navigateToCreateRecipeActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToCreateRecipeActivity() {
        val intent = Intent(this, CreateRecipeActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun showAlertMenu(recipe: Recipe) {
        val items = arrayOf("Edit", "Delete")

        val builder =
            AlertDialog.Builder(this)
        builder.setItems(items) { dialog, which ->
            // the user clicked on colors[which]
            when (which) {
                0 -> {
                    showAlertDialogEdit(recipe)
                }
                1 -> {
                    recipeViewModel.deleteRecipe(recipe)
                }
            }
        }
        builder.show()
    }

    private fun showAlertDialogEdit(recipe: Recipe) {
        val alert = AlertDialog.Builder(this)

        val editText = EditText(applicationContext)
        editText.setText(recipe.recipe_title)

        alert.setTitle("Edit User")
        alert.setView(editText)

        alert.setPositiveButton("Update") { dialog, _ ->
            recipe.recipe_title = editText.text.toString()
            recipeViewModel.updateRecipe(recipe)
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alert.show()
    }
}