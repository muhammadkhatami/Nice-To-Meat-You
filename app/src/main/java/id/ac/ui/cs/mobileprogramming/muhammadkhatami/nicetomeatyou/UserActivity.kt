package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.Manifest.permission.READ_EXTERNAL_STORAGE
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.User
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

    companion object {
        const val PICK_IMAGE_REQUEST_CODE = 1000
        const val READ_EXTERNAL_STORAGE_REQUEST_CODE = 1001
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        userRV.layoutManager = LinearLayoutManager(this)
        userAdapter = UserAdapter(this) { user, i ->
            showAlertMenu(user)
        }
        userRV.adapter = userAdapter

        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        userViewModel.getUsers()?.observe(this, Observer {
            userAdapter.setUsers(it)
        })

        if (ActivityCompat.checkSelfPermission(this, READ_EXTERNAL_STORAGE) ==  PackageManager.PERMISSION_GRANTED) {
            //
        } else {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(android.Manifest.permission.READ_EXTERNAL_STORAGE),
                READ_EXTERNAL_STORAGE_REQUEST_CODE
            )
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu);
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.addMenu -> navigateToCreateUserActivity()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun navigateToCreateUserActivity() {
        val intent = Intent(this, CreateUserActivity::class.java).apply {
        }
        startActivity(intent)
    }

    private fun showAlertMenu(user: User) {
        val items = arrayOf("Edit", "Delete")

        val builder =
            AlertDialog.Builder(this)
        builder.setItems(items) { dialog, which ->
            // the user clicked on colors[which]
            when (which) {
                0 -> {
                    showAlertDialogEdit(user)
                }
                1 -> {
                    userViewModel.deleteUser(user)
                }
            }
        }
        builder.show()
    }

    private fun showAlertDialogEdit(user: User) {
        val alert = AlertDialog.Builder(this)

        val editText = EditText(applicationContext)
        editText.setText(user.username)

        alert.setTitle("Edit User")
        alert.setView(editText)

        alert.setPositiveButton("Update") { dialog, _ ->
            user.username = editText.text.toString()
            userViewModel.updateUser(user)
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alert.show()
    }
}