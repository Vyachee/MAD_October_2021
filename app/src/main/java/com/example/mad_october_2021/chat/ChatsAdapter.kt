package com.example.mad_october_2021.chat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.mad_october_2021.R
import com.example.mad_october_2021.common.Chat
import com.squareup.picasso.Picasso
import de.hdodenhof.circleimageview.CircleImageView

class ChatsAdapter(
    private val items: List<Chat>,
    val context: Context
): RecyclerView.Adapter<ChatsAdapter.Holder>() {


    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val tvName: TextView = itemView.findViewById(R.id.tvName)
        val tvText: TextView = itemView.findViewById(R.id.tvText)
        val ivAvatar: CircleImageView = itemView.findViewById(R.id.ivAvatar)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.chat, parent, false)
        return Holder(view)
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        val chat = items[position]
        val avatar = if(!chat.chat.avatar.isNullOrEmpty())
            chat.chat.avatar
        else chat.lastMessage.user.avatar

        Picasso.get().load(avatar).into(holder.ivAvatar)

        val chatTitle = if(!chat.chat.title.isNullOrEmpty())
            chat.chat.title
        else chat.lastMessage.user.avatar

        holder.tvName.text = chatTitle
        holder.tvText.text = chat.lastMessage.text
    }

    override fun getItemCount(): Int = items.size
}