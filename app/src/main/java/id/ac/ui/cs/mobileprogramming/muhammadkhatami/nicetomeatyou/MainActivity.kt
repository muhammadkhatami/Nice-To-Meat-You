package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TextView(this).apply {
            setText(R.string.category)
        }
        TextView(this).apply {
            setText(R.string.recipe)
        }
        TextView(this).apply {
            setText(R.string.settings)
        }
        setContentView(R.layout.activity_main)

        val category = findViewById(R.id.category_linear_layout) as LinearLayout
        category.setOnClickListener{
            navigateToCategoryActivity()
        }

        val recipe = findViewById(R.id.recipe_linear_layout) as LinearLayout
        recipe.setOnClickListener{
            navigateToRecipeActivity()
        }

        val resultUsers = findViewById(R.id.result_users_linear_layout) as LinearLayout
        resultUsers.setOnClickListener{
            navigateToResultUsersActivity()
        }
    }

    private fun navigateToCategoryActivity() {
        val intent = Intent(this, CategoryActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun navigateToRecipeActivity() {
        val intent = Intent(this, RecipeActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun navigateToResultUsersActivity() {
        val intent = Intent(this, DishActivity::class.java).apply {
        }
        startActivity(intent)
    }
}