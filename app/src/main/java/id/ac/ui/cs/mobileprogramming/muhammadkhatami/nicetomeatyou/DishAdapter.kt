package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.Dish
import kotlinx.android.synthetic.main.item_without_pict.view.*

class DishAdapter(val data: List<Dish>?) : RecyclerView.Adapter<DishAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_without_pict, parent, false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int = data?.size ?: 0

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(data?.get(position))
    }

    class MyHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(get: Dish?) {
            itemView.nama.text = get?.title
            itemView.notelp.text = get?.description

        }
    }
}