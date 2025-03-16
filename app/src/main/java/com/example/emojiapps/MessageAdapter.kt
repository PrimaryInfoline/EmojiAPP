package com.example.emojiapps

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MessageAdapter(private val messages: MutableList<ChatMessage>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return MessageViewHolder(view)
    }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.messageText.text = message.text

        // Устанавливаем GradientDrawable для фона сообщения
        holder.messageBubble.background = message.backgroundDrawable

        // Для улучшения доступности:
        holder.messageText.contentDescription = message.text // Если это текстовое сообщение
        holder.messageBubble.contentDescription = "Message bubble" // Если это сообщение в облаке
    }

    override fun getItemCount() = messages.size

    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.messageText)
        val messageBubble: RelativeLayout = view.findViewById(R.id.message_bubble)
    }
}
