package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(tableName = "notes",
    foreignKeys = arrayOf(
        ForeignKey(entity = Recipe::class, parentColumns = arrayOf("id"),
            childColumns = arrayOf("recipeId"),
            onDelete = ForeignKey.CASCADE)
    ))
data class Note(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int? = null,

    @ColumnInfo(name = "text")
    var text: String?,

    @ColumnInfo(name = "recipeId")
    var recipeId: Int
)