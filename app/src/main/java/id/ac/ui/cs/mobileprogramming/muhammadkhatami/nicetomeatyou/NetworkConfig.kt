package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Dish
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

class NetworkConfig {
    // set interceptor
    fun getInterceptor() : OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        val okHttpClient = OkHttpClient.Builder()
            .addInterceptor(logging)
            .build()
        return  okHttpClient
    }
    fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://my-json-server.typicode.com/muhammadkhatami/demo/")
            .client(getInterceptor())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    fun getService() = getRetrofit().create(Dishes::class.java)
}
interface Dishes {
    @GET("shokugeki-no-soma-fake-API")
    fun getUsers(): Call<List<Dish>>
}