package com.example.kotlin_startup

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.View.OnClickListener
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.kotlin_startup.model.User

class UserAdapter(private val interaction: Interaction? = null) :
    ListAdapter<User, UserAdapter.UserViewHolder>(UserDC()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_row, parent, false), interaction
    )

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) =
        holder.bind(getItem(position))

    fun swapData(data: List<User>) {
        submitList(data.toMutableList())
    }

    inner class UserViewHolder(
        itemView: View,
        private val interaction: Interaction?
    ) : RecyclerView.ViewHolder(itemView), OnClickListener {
        val titleTv: TextView = itemView.findViewById(R.id.titleTV)
        val imageTv: TextView = itemView.findViewById(R.id.imageTV)

        init {
            itemView.setOnClickListener(this)

        }

        override fun onClick(v: View?) {

            if (adapterPosition == RecyclerView.NO_POSITION) return

            val clicked = getItem(adapterPosition)
            interaction?.itemClicked(clicked)

        }

        fun bind(item: User) = with(itemView) {
            // TODO: Bind the data with View
            titleTv.text = item.title
            imageTv.text = item.url


        }

    }

    interface Interaction {
        fun itemClicked(user: User)
    }

    private class UserDC : DiffUtil.ItemCallback<User>() {
        override fun areItemsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            TODO(
                "not implemented"
            )
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: User,
            newItem: User
        ): Boolean {
            TODO(
                "not implemented"
            )
            return oldItem == newItem
        }
    }
}