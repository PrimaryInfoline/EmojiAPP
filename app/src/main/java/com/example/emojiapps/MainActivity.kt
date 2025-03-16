package com.example.emojiapps

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var messageInput: EditText
    private lateinit var sendButton: Button
    private lateinit var adapter: MessageAdapter
    private val messages = mutableListOf<ChatMessage>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recyclerView)
        messageInput = findViewById(R.id.messageInput)
        sendButton = findViewById(R.id.sendButton)

        recyclerView.layoutManager = LinearLayoutManager(this)
        adapter = MessageAdapter(messages)
        recyclerView.adapter = adapter

        sendButton.setOnClickListener {
            val messageText = messageInput.text.toString()
            if (messageText.isNotEmpty()) {
                // Пример: определение цвета фона на основе текста сообщения (эмоции)
                val backgroundColor = when {
                    messageText.contains(":)", ignoreCase = true) -> Color.GREEN
                    messageText.contains(":(", ignoreCase = true) -> Color.RED
                    messageText.contains(":D", ignoreCase = true) -> Color.YELLOW
                    else -> Color.GRAY // Стандартный цвет для нейтральных сообщений
                }

                // Создаем новый объект сообщения с фоновым цветом и текстом
                val newMessage = ChatMessage(messageText, backgroundColor)
                messages.add(newMessage)
                adapter.notifyItemInserted(messages.size - 1)
                messageInput.text.clear()
            }
        }
    }
}


