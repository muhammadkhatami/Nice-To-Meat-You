package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.activity_create_category.*

class CreateCategoryActivity : AppCompatActivity() {

    private lateinit var categoryViewModel: CategoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_category)

        val category = findViewById(R.id.buttonCreateCategory) as Button
        category.setOnClickListener{
            createCategory()
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