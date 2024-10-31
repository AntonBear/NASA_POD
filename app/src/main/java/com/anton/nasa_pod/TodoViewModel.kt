import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.anton.nasa_pod.Todo // Замените на ваш импорт
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class TodoViewModel : ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    private val client = HttpClient() // Создаем HttpClient здесь

    init {
        loadTodos()
    }


    fun loadTodos() {
        viewModelScope.launch {
            try {
                val loadedTodos: List<Todo> = withContext(Dispatchers.IO) {
                    client.get("https://jsonplaceholder.typicode.com/todos").body()
                }
                _todos.postValue(loadedTodos) // Используйте postValue для обновления LiveData из фонового потока

            } catch (e: Exception) {
                Log.e("TodoViewModel", "Error loading todos: ${e.message}", e) // Логируем саму ошибку
                _todos.postValue(emptyList()) // Или другой способ обработки ошибки, например, отображение сообщения об ошибке
            }
            // Закрытие клиента после завершения всех операций
        }
    }

    override fun onCleared() {
        super.onCleared()
        client.close() // Закрываем клиент, когда ViewModel больше не нужна
    }
}