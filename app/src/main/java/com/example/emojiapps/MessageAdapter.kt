package com.example.emojiapps

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

// Адаптер для отображения сообщений
class MessageAdapter(private val messages: MutableList<ChatMessage>) : RecyclerView.Adapter<MessageAdapter.MessageViewHolder>() {

    // Создаем новый ViewHolder для каждого элемента списка
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.message_item, parent, false)
        return MessageViewHolder(view)
    }

    // Привязываем данные (сообщение) к элементам интерфейса
    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        val message = messages[position]
        holder.messageText.text = message.text  // Отображаем текст сообщения
        holder.messageBubble.setBackgroundColor(message.backgroundColor) // Устанавливаем цвет фона для облачка

        // Для улучшения доступности
        holder.messageText.contentDescription = "Message: ${message.text}"
        holder.messageBubble.contentDescription = "Message bubble with background color"
    }

    // Возвращаем количество элементов в списке
    override fun getItemCount() = messages.size

    // Класс ViewHolder для удержания ссылок на элементы интерфейса
    class MessageViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val messageText: TextView = view.findViewById(R.id.messageText)
        val messageBubble: RelativeLayout = view.findViewById(R.id.message_bubble)
    }
}
