package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.RecipeViewModel
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_create_user.*


class CreateRecipeActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    val REQUEST_CODE = 100
    var imageURI = ""
    var selectedSpinner = ""

    var users = arrayOf("Suresh Dasari", "Trishika Dasari", "Rohini Alavala", "Praveen Kumar", "Madhav Sai")

    override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_recipe)

        val profile = findViewById(R.id.buttonCreateRecipe) as Button
        profile.setOnClickListener{
            createRecipe()
        }
        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)

        val pickImage = findViewById(R.id.buttonPickImage) as Button
        pickImage.setOnClickListener{
            openGalleryForImage()
        }

        val spinner = findViewById(R.id.dropdownSpinner) as Spinner

        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, users)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(adapter)
        spinner.setOnItemSelectedListener(this)
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
        val recipeTitle = findViewById(R.id.recipeTitle) as EditText
        val recipeTime = findViewById(R.id.recipeTime) as EditText

        if (selectedSpinner == "")

        recipeViewModel.insertRecipe(
            Recipe(
                recipe_title = selectedSpinner.toString(),
                time = recipeTime.text.toString().toInt(),
                photo = imageURI,
                userId = 1
            )
        )
        onBackPressed()
    }

    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
        selectedSpinner = users[position]
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {

    }
}