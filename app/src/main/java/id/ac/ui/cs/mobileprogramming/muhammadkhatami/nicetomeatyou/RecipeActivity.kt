package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

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

        Log.d("recipeActivityOnCreate", "--------------recipeActivityOnCreate---------------------")

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
        Log.d("onCreateOptionsMenu", "--------------onCreateOptionsMenu---------------------")
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addMenu -> showAlertDialogAdd()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialogAdd() {
        val alert = AlertDialog.Builder(this)
        alert.setTitle("Title")

        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

        val titleText = EditText(applicationContext)
        titleText.hint = "Enter your username"
        layout.addView(titleText)

        val time = EditText(applicationContext)
        time.hint = "Time to cook"
        layout.addView(time) // Notice this is an add method

        val user = EditText(applicationContext)
        user.hint = "user"
        layout.addView(user)

        alert.setView(layout) // Again this is a set method, not add

        alert.setPositiveButton("Save") { dialog, _ ->
            recipeViewModel.insertRecipe(
                Recipe(
                    recipe_title = titleText.text.toString(),
                    time = time.text.toString().toInt(),
                    photo = "test",
                    userId = 1
                )
            )
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alert.show()
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