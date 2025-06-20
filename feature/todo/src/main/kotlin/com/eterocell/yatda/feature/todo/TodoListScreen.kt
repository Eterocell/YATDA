package com.eterocell.yatda.feature.todo

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.eterocell.yatda.core.model.TodoItem
import com.eterocell.yatda.core.ui.TodoItem
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoListScreen(
    modifier: Modifier = Modifier,
    viewModel: TodoListViewModel = viewModel(),
    creationViewModel: TodoCreationViewModel = viewModel(),
) {
    val todos by viewModel.todos.collectAsState()
    val searchQuery by viewModel.searchQuery.collectAsState()

    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    var showCreationSheet by remember { mutableStateOf(false) }

    val coroutineScope = rememberCoroutineScope()

    if (showCreationSheet) {
        ModalBottomSheet(
            onDismissRequest = { showCreationSheet = false },
            sheetState = sheetState,
            dragHandle = null,
        ) {
            TodoCreationBottomSheet(
                viewModel = creationViewModel,
                onDismiss = {
                    coroutineScope.launch {
                        sheetState.hide()
                        showCreationSheet = false
                    }
                },
                onSaveSuccess = {
                    coroutineScope.launch {
                        sheetState.hide()
                        showCreationSheet = false
                    }
                },
            )
        }
    }

    Scaffold(
        modifier = modifier,
        floatingActionButton = {
            ExtendedFloatingActionButton(
                onClick = {
                    creationViewModel.uiState = TodoItem()
                    showCreationSheet = true
                    coroutineScope.launch { sheetState.show() }
                },
                icon = { Icon(Icons.Filled.Add, contentDescription = "New Task") },
                text = { Text(text = "New Task") },
            )
        },
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            TextField(
                value = searchQuery,
                onValueChange = { viewModel.updateSearchQuery(it) },
                label = { Text("Search") },
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
            )

            LazyColumn {
                items(todos, key = { it.id!! }) { todo ->
                    TodoItem(
                        todo = todo,
                        onToggle = { viewModel.toggleCompletion(todo) },
                        onDelete = { viewModel.deleteTodo(todo) },
                    )
                }
            }
        }
    }
}
