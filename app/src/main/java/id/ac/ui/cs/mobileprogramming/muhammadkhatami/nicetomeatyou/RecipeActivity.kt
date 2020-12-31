package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.Manifest
import android.content.DialogInterface
import android.content.Intent
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.adapter.RecipeAdapter
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.RecipeViewModel
import kotlinx.android.synthetic.main.activity_recipe.*

class RecipeActivity : AppCompatActivity() {

    private lateinit var recipeViewModel: RecipeViewModel
    private lateinit var recipeAdapter: RecipeAdapter

    companion object {
        const val PICK_IMAGE_REQUEST_CODE = 1000
        const val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recipe)

        recipeRV.layoutManager = LinearLayoutManager(this)
        recipeAdapter = RecipeAdapter(this) { recipe, i ->
            navigateToDetailRecipeActivity(recipe)
        }

        recipeRV.adapter = recipeAdapter

        recipeViewModel = ViewModelProvider(this).get(RecipeViewModel::class.java)
        recipeViewModel.getRecipes()?.observe(this, Observer {
            recipeAdapter.setRecipes(it)
        })

        chekingPermission()
        triggerAlertPermission()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addMenu -> navigateToCreateRecipeActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToCreateRecipeActivity() {
        val intent = Intent(this, CreateRecipeActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun navigateToDetailRecipeActivity(recipe: Recipe) {
        val intent = Intent(this, DetailRecipeActivity::class.java).apply {
        }
        intent.putExtra("thisRecipe", recipe)
        startActivity(intent)
    }

    private fun chekingPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) ==  PackageManager.PERMISSION_GRANTED) {
            //
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                RecipeActivity.READ_EXTERNAL_STORAGE_REQUEST_CODE
            )
        }
    }

    private fun triggerAlertPermission() {
        if (ActivityCompat.checkSelfPermission(this,
                Manifest.permission.READ_EXTERNAL_STORAGE
            ) ==  PackageManager.PERMISSION_GRANTED) {
            //
        } else {
            showAccessDenyAlert()
        }
    }

    private fun showAccessDenyAlert() {
        val builder =
            AlertDialog.Builder(this)

        builder.setMessage("Nice To Meat You need local storage access to create your recipe. Please grant the permission manually from setting to continue using Nice To Meat You :).")
        builder.setTitle("Wait !")
        builder.setCancelable(false)

        builder
            .setPositiveButton(
                "Close App",
                DialogInterface.OnClickListener { dialog, which ->
                    // then app will close
                    finishAffinity()
                })

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}