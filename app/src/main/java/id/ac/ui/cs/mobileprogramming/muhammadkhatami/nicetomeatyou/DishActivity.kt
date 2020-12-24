package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.Context
import android.content.DialogInterface
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Dish
import kotlinx.android.synthetic.main.activity_dish.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class DishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)

        if (isNetworkAvailable(this)) {

            NetworkConfig().getService()
                .getDishes()
                .enqueue(object : Callback<List<Dish>> {
                    override fun onFailure(call: Call<List<Dish>>, t: Throwable) {
                        Toast.makeText(this@DishActivity, t.localizedMessage, Toast.LENGTH_SHORT)
                            .show()
                    }

                    override fun onResponse(
                        call: Call<List<Dish>>,
                        response: Response<List<Dish>>
                    ) {
                        rvUser.adapter = DishAdapter(response.body())
                    }
                })

        } else {
            showAlertMenu()
        }
    }

    fun isNetworkAvailable(context: Context): Boolean {
        val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetworkInfo: NetworkInfo? = null
        activeNetworkInfo = cm.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting
    }

    private fun showAlertMenu() {
        val builder =
            AlertDialog.Builder(this)

        builder.setMessage("No Internet Connection")
        builder.setTitle("Wait !")
        builder.setCancelable(false)

        builder
            .setPositiveButton(
                "Back",
                DialogInterface.OnClickListener { dialog, which ->
                    // then app will close
                    finish()
                })

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()
    }
}