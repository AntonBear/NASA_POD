package com.anton.nasa_pod
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.serialization.kotlinx.json.json
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : Activity() {
    private val client = HttpClient(Android) { // Android engine для Ktor
        install(ContentNegotiation) {
            json()
        }
    }
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main) // main_activity.xml - основной layout
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)



        // 4. Загрузка данных и обновление RecyclerView в корутине
        CoroutineScope(Dispatchers.Main).launch{
            try {
                Log.d("MainActivity", "Starting network request") // Лог перед запросом
                val todos: List<Todo> = withContext(Dispatchers.IO) {
                    client.get("https://jsonplaceholder.typicode.com/todos").body()
                }
                Log.d("MainActivity", "Received ${todos.size} todos") // Лог после получения данных
                recyclerView.adapter = TodoAdapter(todos)
            } catch (e: Exception) {
                Log.e("MainActivity", "Error: ${e.message}") // Лог ошибки
            } finally {
                client.close()
            }

            try {
                val todos: List<Todo> = withContext(Dispatchers.IO) { // запрос в IO диспетчере
                    client.get("https://jsonplaceholder.typicode.com/todos").body()
                }
                recyclerView.adapter = TodoAdapter(todos)
            } catch (e: Exception) {

            } finally {
                client.close()
            }
        }

    }
}