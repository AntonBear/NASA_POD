import androidx.lifecycle.*
import com.anton.nasa_pod.Repository
import kotlinx.coroutines.launch
import models.Todo

class TodoViewModel(private val repository: Repository) : ViewModel() {

    private val _todos = MutableLiveData<List<Todo>>()
    val todos: LiveData<List<Todo>> = _todos

    init {
        loadTodos()
    }

    fun loadTodos() {
        viewModelScope.launch {
            val loadedTodos = repository.getTodos()
            _todos.value = loadedTodos
        }
    }
}