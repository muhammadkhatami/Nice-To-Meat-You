package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "recipes",
    foreignKeys = arrayOf(
        ForeignKey(entity = User::class, parentColumns = arrayOf("id"),
            childColumns = arrayOf("userId"),
            onDelete = ForeignKey.CASCADE)))
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

    @ColumnInfo(name = "userId")
    var userId: Int
)