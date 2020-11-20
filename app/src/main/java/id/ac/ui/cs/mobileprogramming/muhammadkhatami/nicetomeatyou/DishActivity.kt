package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Dish
import kotlinx.android.synthetic.main.activity_dish.*
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class DishActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dish)

        NetworkConfig().getService()
            .getDishes()
            .enqueue(object : Callback<List<Dish>> {
                override fun onFailure(call: Call<List<Dish>>, t: Throwable) {
                    Toast.makeText(this@DishActivity, t.localizedMessage, Toast.LENGTH_SHORT).show()
                }
                override fun onResponse(
                    call: Call<List<Dish>>,
                    response: Response<List<Dish>>
                ) {
                    rvUser.adapter = DishAdapter(response.body())
                }
            })
    }
}