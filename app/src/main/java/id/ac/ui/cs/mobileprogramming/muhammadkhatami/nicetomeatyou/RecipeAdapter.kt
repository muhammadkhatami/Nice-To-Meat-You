package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*
import kotlinx.android.synthetic.main.item_user.view.*

class RecipeAdapter(private val context: Context?, private val listener: (Recipe, Int) -> Unit) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    private var recipes = listOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = recipes.size

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        if (context != null) {
            holder.bindItem(context, recipes[position], listener)
        }
    }

    fun setRecipes(recipes: List<Recipe>) {
        this.recipes = recipes
        notifyDataSetChanged()
    }
}

class RecipeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(context: Context, recipe: Recipe, listener: (Recipe, Int) -> Unit) {
        itemView.userTV.text = recipe.id.toString() + " - " + recipe.recipe_title
        itemView.userTV1.text = recipe.time.toString() + " second"
        itemView.imageView.setImageURI(Uri.parse(recipe.photo))

        itemView.setOnClickListener {
            listener(recipe, layoutPosition)
        }
    }
}

