package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        TextView(this).apply {
            setText(R.string.profile)
        }
        TextView(this).apply {
            setText(R.string.recipe)
        }
        TextView(this).apply {
            setText(R.string.settings)
        }
        setContentView(R.layout.activity_main)

        Log.d("mainActivityOnCreate", "-------------mainActivityOnCreate--------------")

        val profile = findViewById(R.id.profile_linear_layout) as LinearLayout
        profile.setOnClickListener{
            navigateToProfileActivity()
        }
    }

    private fun navigateToProfileActivity() {
        val intent = Intent(this, UserActivity::class.java).apply {
        }
        startActivity(intent)
    }
}