package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.adapter.CategoryAdapter
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        categoryRV.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(this) { category, i -> }

        categoryRV.adapter = categoryAdapter

        categoryViewModel = ViewModelProvider(this).get(CategoryViewModel::class.java)
        categoryViewModel.getCategories()?.observe(this, Observer {
            categoryAdapter.setCategory(it)
        })
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addMenu -> navigateToCreateCategoryActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToCreateCategoryActivity() {
        val intent = Intent(this, CreateCategoryActivity::class.java).apply {
        }
        startActivity(intent)
    }
}