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
class TodoCreationViewModel
    @Inject
    constructor(
        private val repository: TodoRepository,
    ) : ViewModel() {
        var newItemState by mutableStateOf(TodoItem())

        fun updateTitle(newTitle: String) {
            newItemState = newItemState.copy(title = newTitle)
        }

        fun updateDescription(newDesc: String) {
            newItemState = newItemState.copy(description = newDesc)
        }

        fun loadTodo(id: Uuid) {
            viewModelScope.launch {
                val todo = repository.getTodoById(id) ?: return@launch
                newItemState =
                    TodoItem(
                        id = todo.id,
                        title = todo.title,
                        description = todo.description,
                    )
            }
        }

        fun save() {
            viewModelScope.launch {
                if (newItemState.title.isNotBlank()) {
                    val item =
                        TodoItem(
                            id = newItemState.id,
                            title = newItemState.title,
                            description = newItemState.description,
                        )
                    repository.upsert(item)
                }
            }
        }
    }
