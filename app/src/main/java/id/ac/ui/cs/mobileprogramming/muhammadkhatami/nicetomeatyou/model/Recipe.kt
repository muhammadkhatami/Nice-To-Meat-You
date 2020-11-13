package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "recipes")
data class Recipe(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "recipe_title")
    var recipe_title: String,

    @ColumnInfo(name = "time")
    var time: Int,

    @ColumnInfo(name = "photo")
    var photo: String,

    @ColumnInfo(name = "ownerId")
    var ownerId: String
)