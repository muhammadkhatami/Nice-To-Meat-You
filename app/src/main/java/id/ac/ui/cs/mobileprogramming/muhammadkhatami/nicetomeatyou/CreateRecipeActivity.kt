package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.chip.Chip
import com.google.android.material.chip.ChipGroup
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.activity_create_recipe.*


class CreateRecipeActivity : AppCompatActivity() {

    private lateinit var recipeViewModel: RecipeViewModel

    val REQUEST_CODE = 100
    private var imageURI = ""
    var chipSelected = ""

    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        val recipe = findViewById(R.id.buttonCreateRecipe) as Button
        recipe.setOnClickListener{
            insertRecipe()
        }
        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        val pickImage = findViewById(R.id.buttonPickImage) as Button
        pickImage.setOnClickListener{
            openGalleryForImage()
        }
        val chipGroup = findViewById(R.id.chipGroup) as ChipGroup

        recipeViewModel.allCategories()?.observe(this, Observer { categories ->
            categories?.let {
                for (category: Category in categories) {
                    val chip = LayoutInflater.from(this).inflate(R.layout.chip, null) as Chip
                    chip.text = category.name
                    chip.id = category.id!!
                    chipGroup.addView(chip)
                }
            }
        })
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

    fun insertRecipe() {
        val recipeTitle = findViewById(R.id.recipeTitle) as EditText
        val recipeTime = findViewById(R.id.recipeTime) as EditText

        if(recipeTitle.text !=null && recipeTime.text !=null && chipGroup.checkedChipIds.size != 0) {
            val ids: List<Int> = chipGroup.checkedChipIds

            recipeViewModel.insertRecipe(
                Recipe(
                    recipe_title = recipeTitle.text.toString(),
                    time = recipeTime.text.toString().toInt(),
                    photo = imageURI,
                    categoryId = ids[0]
                )
            )
            onBackPressed()
        }
    }
}