package com.example.emojiapps

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
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
            var messageBackground = createGradientDrawable(Color.GRAY) // Начальный градиент для фона

            // Проверяем на ключевые слова
            when {
                messageText.contains("fun", ignoreCase = true) -> {
                    messageBackground = createGradientDrawable(Color.YELLOW)
                }
                messageText.contains("sad", ignoreCase = true) -> {
                    messageBackground = createGradientDrawable(Color.BLUE)
                }
                messageText.contains("love", ignoreCase = true) -> {
                    messageBackground = createGradientDrawable(Color.MAGENTA) // Розовый для любви
                }
                messageText.contains("angry", ignoreCase = true) -> {
                    messageBackground = createGradientDrawable(Color.RED) // Красный для злости
                }
                // Символьные стикеры
                messageText.contains(":)", ignoreCase = true) -> {
                    messageBackground = createGradientDrawable(Color.YELLOW)
                }
                messageText.contains(":(", ignoreCase = true) -> {
                    messageBackground = createGradientDrawable(Color.BLUE)
                }
                messageText.contains(":*", ignoreCase = true) -> {
                    messageBackground = createGradientDrawable(Color.MAGENTA) // Розовый для любви
                }
            }

            // Проверяем наличие эмодзи в тексте для изменения цвета
            if (messageText.contains("😊") || messageText.contains("😢") || messageText.contains("😍") || messageText.contains("😡")) {
                messageBackground = when {
                    messageText.contains("😊") -> createGradientDrawable(Color.YELLOW)
                    messageText.contains("😢") -> createGradientDrawable(Color.BLUE)
                    messageText.contains("😍") -> createGradientDrawable(Color.MAGENTA) // Розовый для любви
                    messageText.contains("😡") -> createGradientDrawable(Color.RED) // Красный для злости
                    else -> createGradientDrawable(Color.GRAY)
                }
            }

            // Если текст не пустой, создаем сообщение
            if (messageText.isNotEmpty()) {
                val newMessage = ChatMessage(messageText, messageBackground) // передаем GradientDrawable в ChatMessage
                messages.add(newMessage)
                adapter.notifyItemInserted(messages.size - 1)
                messageInput.text.clear()
            }
        }
    }

    // Функция для создания GradientDrawable с переданным цветом
    private fun createGradientDrawable(baseColor: Int): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        gradientDrawable.cornerRadius = 16f // Округлые углы
        gradientDrawable.setColors(intArrayOf(baseColor, baseColor)) // Используем один цвет для градиента
        gradientDrawable.setGradientType(GradientDrawable.LINEAR_GRADIENT) // Тип градиента
        return gradientDrawable
    }
}
