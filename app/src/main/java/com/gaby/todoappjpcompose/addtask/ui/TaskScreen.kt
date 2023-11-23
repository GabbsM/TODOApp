package com.gaby.todoappjpcompose.addtask.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.gaby.todoappjpcompose.addtask.ui.model.TaskModel

@Composable
fun TasksScreen(tasksViewModel: TasksViewModel) {

    val showDialog:Boolean by tasksViewModel.showDialog.observeAsState(false)

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Yellow)){
        AddTasksDialog(showDialog,
                       onDismiss = {tasksViewModel.onDialogClose()},
                       onTaskAdded = {tasksViewModel.onTaskCreated(it)})

        FabDialog(Modifier.align(Alignment.BottomEnd).padding(16.dp),tasksViewModel)
        TasksList(tasksViewModel)

    }
}

@Composable
fun TasksList(tasksViewModel: TasksViewModel) {

    val myTasks : List<TaskModel> = tasksViewModel.task

    LazyColumn {
        items(myTasks, key = {it.id}) { task ->
            ItemTask(task,tasksViewModel)
        }
    }
}

@Composable
fun ItemTask(taskModel: TaskModel,tasksViewModel: TasksViewModel){

    Card(Modifier
        .fillMaxWidth()
        .padding(horizontal = 16.dp, vertical = 8.dp),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {

        Row (Modifier.fillMaxWidth(), verticalAlignment = Alignment.CenterVertically){

            Text(text = taskModel.task, Modifier.weight(1f).padding(16.dp))
            Checkbox(taskModel.selected,{tasksViewModel.onCheckBox(taskModel)})

        }

    }


}
@Composable
fun FabDialog(modifier: Modifier, tasksViewModel: TasksViewModel){

    FloatingActionButton(onClick = {
        tasksViewModel.onShowDialogClick()
    },modifier = modifier){
        Icon(Icons.Filled.Add,"")
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTasksDialog(show:Boolean, onDismiss:() -> Unit, onTaskAdded:(String) -> Unit){

    var myTask by remember { mutableStateOf("") }

    if(show){
        Dialog(onDismissRequest = {onDismiss}){
            Column (Modifier
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)) {
                Text(text = "Agrega tu tarea", fontSize = 16.sp, modifier = Modifier.align(Alignment.CenterHorizontally), fontWeight = FontWeight.Bold)
                Spacer(modifier = Modifier.size(16.dp))
                TextField(value = myTask, onValueChange = {myTask = it}, singleLine = true, maxLines = 1)
                Spacer(modifier = Modifier.size(16.dp))
                Button(onClick = {
                    onTaskAdded(myTask)
                    myTask = ""
                }, modifier = Modifier.fillMaxWidth()){
                    Text(text = "Agrega tarea")
                }

        }
        }
    }

}

