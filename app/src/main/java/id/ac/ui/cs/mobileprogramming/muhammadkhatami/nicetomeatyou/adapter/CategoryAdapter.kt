package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.R
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Category
import kotlinx.android.synthetic.main.item_category.view.*

class CategoryAdapter(private val context: Context?, private val listener: (Category, Int) -> Unit) :
    RecyclerView.Adapter<CategoryViewHolder>() {

    private var categories = listOf<Category>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_category,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = categories.size

    override fun onBindViewHolder(holder: CategoryViewHolder, position: Int) {
        if (context != null) {
            holder.bindItem(context, categories[position], listener)
        }
    }

    fun setCategory(categories: List<Category>) {
        this.categories = categories
        notifyDataSetChanged()
    }
}

class CategoryViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(context: Context, category: Category, listener: (Category, Int) -> Unit) {
        itemView.userTV.text = category.name

        itemView.setOnClickListener {
            listener(category, layoutPosition)
        }
    }

}