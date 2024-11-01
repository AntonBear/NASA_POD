import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.anton.nasa_pod.R
import com.anton.nasa_pod.Repository
import com.anton.nasa_pod.RepositoryImp
import com.anton.nasa_pod.TodoAdapter
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android


class MainActivity : AppCompatActivity() {

    private val viewModel: TodoViewModel by viewModels {
        val client = HttpClient(Android)
        val repository = RepositoryImp(client)
        TodoViewModelFactory(repository)
    }

    private lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)


        viewModel.todos.observe(this) { todos ->
            val adapter = TodoAdapter(todos)
            recyclerView.adapter = adapter
        }
    }
}

class TodoViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TodoViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return TodoViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}