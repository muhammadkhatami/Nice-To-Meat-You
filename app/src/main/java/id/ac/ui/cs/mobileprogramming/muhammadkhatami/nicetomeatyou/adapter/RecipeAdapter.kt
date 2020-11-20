package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.adapter

import android.content.Context
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.R
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Recipe
import kotlinx.android.synthetic.main.item_recipe.view.*

class RecipeAdapter(private val context: Context?, private val listener: (Recipe, Int) -> Unit) :
    RecyclerView.Adapter<RecipeViewHolder>() {

    private var recipes = listOf<Recipe>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        return RecipeViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_recipe,
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
        itemView.recipeTV.text = recipe.recipe_title
        itemView.recipeTV1.text = recipe.time.toString() + " tick"
        itemView.imageView.setImageURI(Uri.parse(recipe.photo))

        itemView.setOnClickListener {
            listener(recipe, layoutPosition)
        }
    }
}

