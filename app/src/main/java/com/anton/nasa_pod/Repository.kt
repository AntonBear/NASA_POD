package com.anton.nasa_pod

import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


interface Repository {
    suspend fun getTodos(): List<models.Todo>
}

class RepositoryImp (private val client: HttpClient) : Repository {

    override suspend fun getTodos(): List<models.Todo> {
        return try {
            client.get("https://jsonplaceholder.typicode.com/todos").body<List<models.Todo>>()
        } catch (e: Exception) {
            emptyList<models.Todo>()
        }
    }
}