package com.anton.nasa_pod

import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.request.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable


@Serializable
data class Todo(
    val userId: Int,
    val id: Int,
    val title: String,
    val completed: Boolean,
)

suspend fun main() {
    val client = HttpClient(CIO) {
        install(ContentNegotiation) {
            json()
        }
    }

    try {
        val todos: List<Todo> = client.get("https://jsonplaceholder.typicode.com/todos").body()
        todos.forEach { todo ->
            println("ID: ${todo.id}, Title: ${todo.title}, Completed: ${todo.completed}")
        }
    } catch (e: Exception) {
        println("Error: ${e.message}")
    } finally {
        client.close()
    }
}