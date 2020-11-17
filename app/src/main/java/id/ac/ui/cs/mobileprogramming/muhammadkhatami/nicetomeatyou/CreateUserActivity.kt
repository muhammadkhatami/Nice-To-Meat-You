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
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.User
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.activity_create_user.*


class CreateUserActivity : AppCompatActivity() {

    private lateinit var userViewModel: UserViewModel
    private lateinit var userAdapter: UserAdapter
    val REQUEST_CODE = 100
    var imageURI = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val profile = findViewById(R.id.buttonCreateUser) as Button
        profile.setOnClickListener{
            createUser()
        }
        val imageView = findViewById(R.id.imageView) as ImageView

        val pickImage = findViewById(R.id.buttonPickImage) as Button
        pickImage.setOnClickListener{
            openGalleryForImage()
        }
        userViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
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

    fun createUser() {
        val username = findViewById(R.id.username) as EditText
        val email = findViewById(R.id.email) as EditText

        userViewModel.insertUser(
            User(
                username = username.text.toString(),
                email = email.text.toString(),
                image = imageURI
            )
        )
        onBackPressed()
    }
}