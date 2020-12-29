package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.TextView
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.opengl.MyGLSurfaceView

class SplashActivity : AppCompatActivity() {

    private val SPLASH_TIME_OUT:Long = 2500
    private lateinit var glSurfaceView: MyGLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
//        TextView(this).apply {
//            setText(R.string.app_description)
//        }
//        setContentView(R.layout.activity_splash)

        glSurfaceView = MyGLSurfaceView(this)
        setContentView(glSurfaceView)

        Handler().postDelayed({
            startActivity(Intent(this,MainActivity::class.java))
            finish()
        }, SPLASH_TIME_OUT)
    }
}