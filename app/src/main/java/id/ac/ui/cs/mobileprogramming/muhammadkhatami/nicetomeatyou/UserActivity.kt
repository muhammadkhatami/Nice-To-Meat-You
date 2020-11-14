package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.os.Bundle
import android.util.Log
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
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.User
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter

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

        val usernameText = EditText(applicationContext)
        usernameText.hint = "Enter your username"

        alert.setTitle("New User")
        alert.setView(usernameText)

        val layout = LinearLayout(applicationContext)
        layout.orientation = LinearLayout.VERTICAL

        // Add a TextView here for the "Title" label, as noted in the comments

        // Add a TextView here for the "Title" label, as noted in the comments
        val usernameBox = EditText(applicationContext)
        usernameBox.hint = "Username"
        layout.addView(usernameBox) // Notice this is an add method

        val emailBox = EditText(applicationContext)
        emailBox.hint = "Email"
        layout.addView(emailBox)


        alert.setView(layout) // Again this is a set method, not add

        alert.setPositiveButton("Save") { dialog, _ ->
            userViewModel.insertUser(
                User(
                    username = usernameBox.text.toString(),
                    email = emailBox.text.toString()
                )
            )
            dialog.dismiss()
        }

        alert.setNegativeButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        alert.show()
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