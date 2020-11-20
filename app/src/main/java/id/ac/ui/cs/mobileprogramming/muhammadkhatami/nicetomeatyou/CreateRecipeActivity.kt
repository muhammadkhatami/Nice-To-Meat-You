package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.CategoryViewModel
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.activity_create_recipe.*


class CreateRecipeActivity : AppCompatActivity() {

    private lateinit var recipeViewModel: RecipeViewModel

    val REQUEST_CODE = 100
    var imageURI = ""
    var chipSelected = ""

    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        val recipe = findViewById(R.id.buttonCreateRecipe) as Button
        recipe.setOnClickListener{
            createRecipe()
        }
        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        val pickImage = findViewById(R.id.buttonPickImage) as Button
        pickImage.setOnClickListener{
            openGalleryForImage()
        }
        val chipGroup = findViewById(R.id.chipGroup) as ChipGroup

        val chipWinterFood = findViewById(R.id.chipWinterFood) as Chip
        chipGroup.setOnClickListener {
            // Responds to chip click
        }
        chipGroup.setOnCheckedChangeListener { chipGroup, i ->
            // Responds to chip checked/unchecked
            getSelectedText(chipGroup, i)
        }
    }

    private fun getSelectedText(chipGroup: ChipGroup, id: Int): String {
        val mySelection = chipGroup.findViewById(id) as Chip
        chipSelected = mySelection?.text?.toString() ?: ""
        return mySelection?.text?.toString() ?: ""
    }

    private fun openGalleryForImage() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (resultCode == Activity.RESULT_OK && requestCode == REQUEST_CODE){
            imageView.setImageURI(data?.data) // handle chosen image
            imageURI = data?.data.toString()
        }
    }


    fun createRecipe() {

        Log.d("---------", "---------------------------------------------")
        val recipeTitle = findViewById(R.id.recipeTitle) as EditText
        val recipeTime = findViewById(R.id.recipeTime) as EditText


        recipeViewModel.insertRecipe(
            Recipe(
                recipe_title = recipeTitle.text.toString(),
                time = recipeTime.text.toString().toInt(),
                photo = imageURI
            )
        )
        onBackPressed()
    }
}