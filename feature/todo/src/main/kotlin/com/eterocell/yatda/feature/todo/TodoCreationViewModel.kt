package com.eterocell.yatda.feature.todo

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eterocell.yatda.core.data.repository.TodoRepository
import com.eterocell.yatda.core.model.TodoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject
import kotlin.uuid.Uuid

@HiltViewModel
class TodoCreationViewModel @Inject constructor(
    private val repository: TodoRepository,
) : ViewModel() {

    var uiState by mutableStateOf(TodoItem())

    fun updateTitle(newTitle: String) {
        uiState = uiState.copy(title = newTitle)
    }

    fun updateDescription(newDesc: String) {
        uiState = uiState.copy(description = newDesc)
    }

    fun loadTodo(id: Uuid) {
        viewModelScope.launch {
            val todo = repository.getTodoById(id) ?: return@launch
            uiState = TodoItem(
                id = todo.id,
                title = todo.title,
                description = todo.description,
            )
        }
    }

    fun save() {
        viewModelScope.launch {
            if (uiState.title.isNotBlank()) {
                val item = TodoItem(
                    id = uiState.id,
                    title = uiState.title,
                    description = uiState.description,
                )
                repository.upsert(item)
            }
        }
    }
}
