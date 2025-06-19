package com.eterocell.yatda.feature.todo

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.eterocell.yatda.core.data.repository.TodoRepository
import com.eterocell.yatda.core.model.TodoItem
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.datetime.Clock
import javax.inject.Inject

@HiltViewModel
@OptIn(ExperimentalCoroutinesApi::class)
class TodoListViewModel
    @Inject
    constructor(
        private val repository: TodoRepository,
    ) : ViewModel() {
        private val _searchQuery = MutableStateFlow("")
        val searchQuery: StateFlow<String> = _searchQuery.asStateFlow()

        val todos: StateFlow<List<TodoItem>> =
            _searchQuery
                .debounce(200) // if you want debounce search
                .flatMapLatest { query ->
                    if (query.isBlank()) {
                        repository.getAllTodos()
                    } else {
                        repository.searchTodos(query)
                    }
                }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

        fun updateSearchQuery(query: String) {
            _searchQuery.value = query
        }

        fun toggleCompletion(todo: TodoItem) {
            viewModelScope.launch {
                if (todo.isCompleted) {
                    repository.unmarkAsCompleted(todo)
                } else {
                    repository.markAsCompleted(todo)
                }
            }
        }

        fun deleteTodo(todo: TodoItem) {
            viewModelScope.launch {
                repository.delete(todo)
            }
        }

        fun addTodo(
            title: String,
            description: String? = null,
        ) {
            viewModelScope.launch {
                val now = Clock.System.now()
                val todo =
                    TodoItem(
                        id = null, // mapper will generate UUID
                        creationInstant = now,
                        title = title,
                        description = description,
                        isCompleted = false,
                        completedDateInstant = null,
                        lastModifiedInstant = now,
                        relevantInstant = now,
                        isImportant = false,
                    )
                repository.upsert(todo)
            }
        }
    }
