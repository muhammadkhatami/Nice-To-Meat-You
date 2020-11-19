package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model

import com.google.gson.annotations.SerializedName

data class Dish(
    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("title")
    val title: String? = null,

    @field:SerializedName("description")
    val description: String? = null
)