package id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.R
import id.ac.ui.cs.mobileprogramming.muhammadkhatami.nicetomeatyou.model.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserAdapter(private val context: Context?, private val listener: (User, Int) -> Unit) :
    RecyclerView.Adapter<UserViewHolder>() {

    private var users = listOf<User>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        return UserViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_user,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = users.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        if (context != null) {
            holder.bindItem(context, users[position], listener)
        }
    }

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }
}

class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItem(context: Context, user: User, listener: (User, Int) -> Unit) {
        itemView.userTV.text = user.id.toString() + " - " + user.username + " - " + user.email

        itemView.setOnClickListener {
            listener(user, layoutPosition)
        }
    }

}