package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.activity_create_user.*


class CreateCategoryActivity : AppCompatActivity() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter
    val REQUEST_CODE = 100
    var imageURI = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val category = findViewById(R.id.buttonCreateCategory) as Button
        category.setOnClickListener{
            createCategory()
        }
        val imageView = findViewById(R.id.imageView) as ImageView

        val pickImage = findViewById(R.id.buttonPickImage) as Button
        pickImage.setOnClickListener{
            openGalleryForImage()
        }
        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
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

    fun createCategory() {
        val name = findViewById(R.id.name) as EditText

        categoryViewModel.insertCategory(
            Category(
                name = name.text.toString()
            )
        )
        onBackPressed()
    }
}