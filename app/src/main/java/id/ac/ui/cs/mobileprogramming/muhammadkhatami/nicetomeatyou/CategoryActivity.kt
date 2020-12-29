package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.adapter.CategoryAdapter
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.CategoryViewModel
import kotlinx.android.synthetic.main.activity_category.*

class CategoryActivity : AppCompatActivity() {

    private lateinit var categoryViewModel: CategoryViewModel
    private lateinit var categoryAdapter: CategoryAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_category)

        categoryRV.layoutManager = LinearLayoutManager(this)
        categoryAdapter = CategoryAdapter(this) { category, i ->
            showAlertMenu(category)
        }

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
            R.id.addMenu -> showAlertDialogAdd()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun showAlertDialogAdd() {
        val alert = AlertDialog.Builder(this)

        alert.setTitle("${getString(R.string.category)}")

        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

        val nameBox = EditText(applicationContext)
        nameBox.hint = "${getString(R.string.name)}"
        layout.addView(nameBox)
        alert.setView(layout)

        alert.setPositiveButton("\"${getString(R.string.save)}\"") { dialog, _ ->
            categoryViewModel.insertCategory(
                Category(
                    name = nameBox.text.toString()
                )
            )
            dialog.dismiss()
        }
        alert.setNegativeButton("${getString(R.string.cancel)}") { dialog, _ ->
            dialog.dismiss()
        }
        alert.show()
    }

    private fun showAlertMenu(category: Category) {
        val items = arrayOf("${getString(R.string.edit)}", "${getString(R.string.delete)}")

        val builder =
            AlertDialog.Builder(this)
        builder.setItems(items) { dialog, which ->
            // the user clicked on colors[which]
            when (which) {
                0 -> {
                    showAlertDialogEdit(category)
                }
                1 -> {
                    categoryViewModel.deleteCategory(category)
                }
            }
        }
        builder.show()
    }

    private fun showAlertDialogEdit(category: Category) {
        val alert = AlertDialog.Builder(this)

        val editText = EditText(applicationContext)
        editText.setText(category.name)

        alert.setTitle("\"${getString(R.string.edit)}\"")
        alert.setView(editText)

        alert.setPositiveButton("\"${getString(R.string.update)}\"") { dialog, _ ->
            category.name = editText.text.toString()
            categoryViewModel.updateCategory(category)
            dialog.dismiss()
        }

        alert.setNegativeButton("\"${getString(R.string.cancel)}\"") { dialog, _ ->
            dialog.dismiss()
        }

        alert.show()
    }
}