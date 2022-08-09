package com.example.testaxiata.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testaxiata.R
import com.example.testaxiata.data.model.User

class UserAdapter(var item: MutableList<User>?)
    : RecyclerView.Adapter<UserAdapter.ViewHolder>() {

    var listener: AdapterClickListener<User>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_view_list_user, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        item?.get(position)?.let {
            holder.bind(it)
        }
    }

    override fun getItemCount(): Int {
        return item?.size ?: 0
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val ivUser: AppCompatImageView = view.findViewById(R.id.ivUser)
        private val tvName: AppCompatTextView = view.findViewById(R.id.tvName)
        private val tvEmail: AppCompatTextView = view.findViewById(R.id.tvEmail)

        fun bind(item: User) {
            Glide.with(itemView.context)
                .load(item.avatar)
                .into(ivUser)
            tvName.text = item.first_name + " " + item.last_name
            tvEmail.text = item.email

            itemView.setOnClickListener {
                listener?.onItemClick(item)
                notifyDataSetChanged()
            }
        }
    }

    fun addData(item: List<User>) {
        this.item?.addAll(item)
        notifyDataSetChanged()
    }
}