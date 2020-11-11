package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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

        // get reference to button
        val profile = findViewById(R.id.profile_linear_layout) as LinearLayout
        profile.setOnClickListener{
            navigateToProfileActivity()
        }
    }

    fun navigateToProfileActivity() {
//        val intent = Intent(this, UserActivity::class.java).apply {
//        }
//        startActivity(intent)
    }
}